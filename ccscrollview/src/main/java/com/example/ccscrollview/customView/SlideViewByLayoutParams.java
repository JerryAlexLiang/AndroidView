package com.example.ccscrollview.customView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 创建日期：2017/9/20 on 上午10:46
 * 描述:实现View滑动的六种方法-way03: 使用LayoutParams
 * LayoutParams主要保存了一个View的布局参数
 * 作者:yangliang
 */
public class SlideViewByLayoutParams extends View {

    private int lastX;
    private int lastY;

    public SlideViewByLayoutParams(Context context) {
        super(context);
    }

    public SlideViewByLayoutParams(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideViewByLayoutParams(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                //因为父控件是RelativeLayout则要使用RelativeLayout.LayoutParams
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft()+offsetX;
//                layoutParams.topMargin = getTop()+offsetY;
//                setLayoutParams(layoutParams);

                //除了使用布局的LayoutParams外，还可以用ViewGroup.MarginLayoutParams来实现：
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;

        }

        return true;
    }
}
