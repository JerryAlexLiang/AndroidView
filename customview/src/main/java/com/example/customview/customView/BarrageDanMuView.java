package com.example.customview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customview.BulletItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 创建日期：2017/12/2 on 下午11:29
 * 描述:自定义View实现弹幕效果
 * 作者:yangliang
 */
public class BarrageDanMuView extends View {

    private int count;
    private List<BulletItem> bulletItems = new ArrayList<>();
    private Paint mPaint;
    private int randomX = 800;
    private int randomBaseline;
    private Random random = new Random();
    private Handler mHander;

    public BarrageDanMuView(Context context) {
        this(context, null);
    }


    public BarrageDanMuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarrageDanMuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        HandlerThread handlerThread = new HandlerThread("ss");
        handlerThread.start();
        mHander = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                postInvalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.WHITE);
        if (count % 5 == 0) {

            int ran = random.nextInt(500);
            randomBaseline = ran + 60;
            BulletItem bulletItem = new BulletItem(randomX, randomBaseline, ran);
            bulletItems.add(bulletItem);
        }

        for (int i = 0; i < bulletItems.size(); i++) {
            BulletItem bulletItem = bulletItems.get(i);
            bulletItem.move(canvas, mPaint);
            if (bulletItem.x <= -200) {
                bulletItems.remove(i);
            }
        }

        count++;
        mHander.sendEmptyMessageDelayed(1, 100);
    }
}
