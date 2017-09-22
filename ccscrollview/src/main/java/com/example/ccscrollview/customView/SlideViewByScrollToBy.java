package com.example.ccscrollview.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/**
 * 创建日期：2017/9/20 on 上午10:46
 * 描述:实现View滑动的六种方法-way04: scollTo与scollBy
 * scollTo(x,y)表示移动到一个具体的坐标点，而scollBy(dx,dy)则表示移动的增量为dx、dy。其中scollBy最终也是要调用scollTo的。
 * scollTo、scollBy移动的是View的内容，如果在ViewGroup中使用则是移动他所有的子View。
 * 这里要实现CustomView随着我们手指移动的效果的话，我们就需要将偏移量设置为负值。
 * <p>
 * 注意：我们用scollTo/scollBy方法来进行滑动时，这个过程是瞬间完成的，所以用户体验不大好
 * 作者:yangliang
 */
public class SlideViewByScrollToBy extends View {

    private int lastX;
    private int lastY;

    public SlideViewByScrollToBy(Context context) {
        super(context);
    }

    public SlideViewByScrollToBy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideViewByScrollToBy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在onTouchEvent()方法中获取触摸点的坐标：
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取到手指处的横坐标和纵坐标(点击事件距离控件的距离)
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            //接下来在ACTION_MOVE事件中计算偏移量，再调用layout()方法重新放置这个自定义View的位置
            //每次移动时都会调用layout()方法来对自己重新布局，从而达到移动View的效果。

            case MotionEvent.ACTION_MOVE:
                //计算移动的距离（偏移量）
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //使用scrollBy
                //这里要实现CustomView随着我们手指移动的效果的话，我们就需要将偏移量设置为负值
                View view = (View) getParent();
                view.scrollBy(-offsetX, -offsetY);
                break;

        }

        return true;
    }
}
