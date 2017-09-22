package com.example.customview.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 创建日期：2017/9/22 on 下午5:37
 * 描述:自定义的ViewGroup，左右滑动切换不同的页面，类似一个特别简化的ViewPager，
 * 这里会涉及到这个系列的很多文章的内容比如View的measure、layout和draw流程，view的滑动等等
 * <p>
 * 这里定义了名字叫CustomHorizontalView的类并继承 ViewGroup，onLayout这个抽象方法是必须要实现的
 * 作者:yangliang
 */
public class CustomHorizontalView extends ViewGroup {

    private int lastX;
    private int lastY;
    private int currentIndex = 0; //当前子元素
    private int childWidth = 0;
    private Scroller scroller;
    private VelocityTracker tracker;    //增加速度检测,如果速度比较快的话,就算没有滑动超过一半的屏幕也可以
    private int lastInterceptX = 0;
    private int lastInterceptY = 0;

    public CustomHorizontalView(Context context) {
        super(context);
        init();
    }

    public CustomHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    /**
     * intercept的拦截逻辑
     * 处理滑动冲突
     * 要实现在弹性滑动过程中再次触摸拦截，肯定要在onInterceptTouchEvent中的ACTION_DOWN中去判断，如果在ACTION_DOWN的时候，
     * scroller还没有完成，说明上一次的滑动还正在进行中，则直接中断scroller：
     *
     * @param
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean intercept = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {

            //再次触摸屏幕阻止页面继续滑动
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                //如果动画还没有执行完成,则打断
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptY;
                //水平方向距离长  MOVE中返回true一次,后续的MOVE和UP都不会收到此请求
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                    Log.i("tag", "intercept = true");
                } else {
                    intercept = false;
                    Log.i("tag", "intercept = false");
                }
                break;

            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        //因为DOWN返回true,所以onTouchEvent中无法获取DOWN事件,所以这里要负责设置lastX,lastY
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    /**
     * 滑动处理，快速滑动到其他页面
     * 我们不只滑动超过一半才切换到上/下一个页面，如果滑动速度很快的话，我们也可以判定为用户想要滑动到其他页面，这样的体验也是好的。
     * 这部分也是在onTouchEvent中的ACTION_UP部分：这里又需要用到VelocityTracker，它用来测试滑动速度的。使用方法也很简单，
     * 首先在构造函数中进行初始化，也就是前面的init方法中增加一条语句：
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                //跟随手指滑动
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;

            //释放手指以后开始自动滑动到目标位置
            case MotionEvent.ACTION_UP:
                //相对于当前View滑动的距离,正为向左,负为向右
                int distance = getScrollX() - currentIndex * childWidth;

                //必须滑动的距离要大于1/2个宽度,否则不会切换到其他页面
                if (Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    //调用该方法计算1000ms内滑动的平均速度
                    tracker.computeCurrentVelocity(1000);
                    //获取到水平方向上的速度
                    float xV = tracker.getXVelocity();
                    //如果速度的绝对值大于50的话，就认为是快速滑动，就执行切换页面
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            //大于0切换上一个页面(向右滑动，回到上一个页面)
                            currentIndex--;
                        } else {
                            //小于0切换到下一个页面（向左滑动，滑到下一个页面）
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                //重置速度计算器
                tracker.clear();
                break;

            default:
                break;
        }

        lastX = x;
        lastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 弹性滑动到其他页面
     *
     * @param destX
     * @param destY
     */
    private void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 1000);
        invalidate();
    }

    /**
     * 对wrap_content属性进行处理
     * 这里如果没有子元素时采用了简化的写法直接将宽和高直接设置为0，
     * 正常的话我们应该根据LayoutParams中的宽和高来做相应的处理，
     * 另外我们在测量时没有考虑它的padding和子元素的margin。
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量所有子元素,计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //处理wrap_content的情况
        if (getChildCount() == 0) {
            //如果没有子元素，就设置宽高都为0（简化处理）
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //宽和高都是AT_MOST，则设置宽度所有子元素的宽度的和；高度设置为第一个元素的高度；
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //如果宽度是wrap_content，则宽度为所有子元素的宽度的和
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //如果高度是wrap_content，则高度为第一个子元素的高度
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight);
        }
    }

    /**
     * 实现onLayout
     * 实现onLayout，来布局子元素，因为每一种布局方式子View的布局都是不同的，
     * 所以这个是ViewGroup唯一一个抽象方法，需要我们自己去实现：
     * 遍历所有的子元素，如果子元素不是GONE，则调用子元素的layout方法将其放置到合适的位置上，相当于默认第一个子元素
     * 占满了屏幕，后面的子元素就是在第一个屏幕后面紧挨着和屏幕一样大小的后续元素，所以left是一直累加的，top保持0，
     * bottom保持第一个元素的高度，right就是left+元素的宽度，同样这里没有处理自身的pading以及子元素的margin。
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //左边的距离
        int left = 0;
        View child;
        //遍历布局子元素
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                //赋值给子元素宽度变量
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }
}
