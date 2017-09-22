package com.example.ccscrollview.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建日期：2017/9/20 on 上午10:46
 * 描述:实现View滑动的六种方法-way02: offsetLeftAndRight()与offsetTopAndBottom()
 * 作者:yangliang
 */
public class SlideViewByOffset extends View {

    private int lastX;
    private int lastY;

    public SlideViewByOffset(Context context) {
        super(context);
    }

    public SlideViewByOffset(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideViewByOffset(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

            case MotionEvent.ACTION_MOVE:
                //计算移动的距离（偏移量）
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //对left和right进行偏移
                offsetLeftAndRight(offsetX);
                //对top和bottom进行偏移
                offsetTopAndBottom(offsetY);
                break;

        }

        return true;
    }
}
