package com.example.customview.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 创建日期：2017/12/2 on 下午9:31
 * 描述:圆环大小变换
 * 作者:yangliang
 */
public class MyRing extends View {

    int cx;
    int cy;
    int radius;
    Paint mPaint;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 1. 半径变大
            // 2. 宽度变大
            // 3. 颜色变淡
            radius += 5;
            mPaint.setStrokeWidth(radius / 3);
            int alpha = mPaint.getAlpha();// 当前透明度
            alpha -= 10;

            if (alpha < 0) {
                alpha = 0;
            }

            mPaint.setAlpha(alpha);

            invalidate();

            if (alpha > 0) {// 只有圆环没有完全消失,才继续绘制
                mHandler.sendEmptyMessageDelayed(0, 50);// 延时后继续绘制圆环
            }
        }
    };

    public MyRing(Context context) {
        super(context);
        initView();
    }

    public MyRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyRing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        radius = 0;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(radius / 3);
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(255);// 设置透明度0-255, 255表示完全不透明

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cx = (int) event.getX();
                cy = (int) event.getY();

                // 重新初始化数据
                initView();

                mHandler.sendEmptyMessage(0);
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
