package com.example.ccscrollview.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建日期：2017/9/20 on 上午10:46
 * 描述:实现View滑动的六种方法-way01: layout()
 * 每次移动时都会调用layout()方法来对自己重新布局，从而达到移动View的效果。
 * 作者:yangliang
 */
public class SlideViewByLayout extends View {

    private int lastX;
    private int lastY;

    public SlideViewByLayout(Context context) {
        super(context);
    }

    public SlideViewByLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideViewByLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                //调用layout方法来重新放置它的位置
                layout(getLeft() + offsetX, getTop() + offsetY,
                        getRight() + offsetX, getBottom() + offsetY);
                break;

        }

        return true;
    }
}
