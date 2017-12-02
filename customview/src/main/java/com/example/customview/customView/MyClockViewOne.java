package com.example.customview.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * 创建日期：2017/12/2 on 下午10:30
 * 描述:自定义View实现时钟-样式一
 * 作者:yangliang
 */
public class MyClockViewOne extends View {

    private int centerX = 200;
    private int centerY = 200;
    private int radius = 150;

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private MyClockHandler myClockHandler;
    private Paint mPaint;

    public MyClockViewOne(Context context) {
        this(context, null);
    }

    public MyClockViewOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClockViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        myClockHandler = new MyClockHandler();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
//        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
//        canvas.drawCircle(centerX, centerY, radius, mPaint);

        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 12; i++) {
            canvas.save();
            if (i == 0 || i == 3 || i == 6 || i == 9) {
                mPaint.setStyle(Paint.Style.FILL);
            } else {
                mPaint.setStyle(Paint.Style.STROKE);
            }
            canvas.rotate(30 * i, centerX, centerY);
//            canvas.drawLine(200, 50, 200, 60, mPaint);
            canvas.drawCircle(200, 55, 5, mPaint);
            canvas.restore();
        }

        //时针
        canvas.save();
        mPaint.setStrokeWidth(10);
        float hourDegee = hours * 30f + minutes * 0.5f;
        canvas.rotate(hourDegee, centerX, centerY);
        canvas.drawLine(200, 195, 200, 120, mPaint);
        canvas.restore();
        //分针
        canvas.save();
        mPaint.setStrokeWidth(5);
        float minutesDegee = minutes * 6;
        canvas.rotate(minutesDegee, centerX, centerY);
        canvas.drawLine(200, 200, 200, 70, mPaint);
        canvas.restore();
        //秒针
        canvas.save();
        float secondeDegee = seconds * 6;
        canvas.rotate(secondeDegee, centerX, centerY);
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(100);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 55, 10, mPaint);
        canvas.restore();

        myClockHandler.sendEmptyMessageDelayed(1, 1000);


    }


    @SuppressLint("HandlerLeak")
    class MyClockHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Calendar instance = Calendar.getInstance();
            hours = instance.get(Calendar.HOUR);
            minutes = instance.get(Calendar.MINUTE);
            seconds = instance.get(Calendar.SECOND);

            invalidate();
//            sendEmptyMessage(1);
//            sendEmptyMessageDelayed(1, 1000);

        }
    }
}
