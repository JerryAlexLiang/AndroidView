package com.example.ccscrollview.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * 创建日期：2017/9/20 on 上午10:46
 * 描述:实现View滑动的六种方法-way05: Scroller
 * 我们用scollTo/scollBy方法来进行滑动时，这个过程是瞬间完成的，所以用户体验不大好。
 * 这里我们可以使用Scroller来实现有过度效果的滑动，这个过程不是瞬间完成的，而是在一定的时间间隔完成的。
 * Scroller本身是不能实现View的滑动的，它需要配合View的computeScroll()方法才能弹性滑动的效果。
 * 在这里我们实现CustomView平滑的向右移动。
 * 作者:yangliang
 */
public class SlideViewByScroller extends View {

    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public SlideViewByScroller(Context context) {
        super(context);
    }

    public SlideViewByScroller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //首先我们要初始化Scroller
        mScroller = new Scroller(context);
    }

    public SlideViewByScroller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    /**
//     * 在onTouchEvent()方法中获取触摸点的坐标：
//     *
//     * @param event
//     * @return
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //获取到手指处的横坐标和纵坐标(点击事件距离控件的距离)
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                lastX = x;
//                lastY = y;
//                break;
//
//            //接下来在ACTION_MOVE事件中计算偏移量，再调用layout()方法重新放置这个自定义View的位置
//            //每次移动时都会调用layout()方法来对自己重新布局，从而达到移动View的效果。
//
//            case MotionEvent.ACTION_MOVE:
//                //计算移动的距离（偏移量）
//                int offsetX = x - lastX;
//                int offsetY = y - lastY;
//                //调用layout方法来重新放置它的位置
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);
//                break;
//
//        }
//
//        return true;
//    }


    /**
     * 接下来重写computeScroll()方法，系统会在绘制View的时候在draw()方法中调用该方法，
     * 这个方法中我们调用父类的scrollTo()方法并通过Scroller来不断获取当前的滚动值，
     * 每滑动一小段距离我们就调用invalidate()方法不断的进行重绘，重绘就会调用computeScroll()方法，
     * 这样我们就通过不断的移动一个小的距离并连贯起来就实现了平滑移动的效果：
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            View view = (View) getParent();
            view.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过不断地重绘不断的调用computeScroll方法
            invalidate();
        }
    }

    /**
     * 调用Scroller.startScroll()方法。我们在CustomView中写一个smoothScrollTo()方法，
     * 调用Scroller.startScroll()方法，在2000毫秒内沿X轴平移delta像素：
     * 最后我们在Activity.java中调用CustomView的smoothScrollTo()方法：
     */
    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        int scrollY = getScrollY();
        int deltaY = destY - scrollY;
        //2000秒内滑向destX
//        mScroller.startScroll(scrollX, 0, delta, 0, 2000);
        mScroller.startScroll(scrollX, scrollY, delta, deltaY, 2000);
        invalidate();
    }
}
