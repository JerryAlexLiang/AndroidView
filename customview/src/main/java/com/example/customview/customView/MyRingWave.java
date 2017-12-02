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

import java.util.ArrayList;

/**
 * 创建日期：2017/12/2 on 下午9:40
 * 描述:自定义View实现水波纹效果
 * 开发流程:
 * 1. 重写onTouchEvent,ACTION_DOWN,ACTION_MOVE
 * 2. 封装Ripple对象
 * 3. 圆环集合mRippleList
 * 4. addPoint(x,y), 第一次进入,启动绘制
 * 5. 在Handler接受消息
 * 6. flushData,刷新集合中所有圆环的属性
 * 7. invalidate,刷新界面, 继续发消息,形成内循环
 * 8. onDraw, 绘制所有圆环
 * 9. 圆环间距, 透明度变化速度, 宽度变化速度
 * 作者:yangliang
 */
public class MyRingWave extends View {

    private static final int MIN_DIS = 10;// 圆环最小间距

    // 圆环集合
    private ArrayList<Wave> mWaveList = new ArrayList<MyRingWave.Wave>();

    private int[] mColors = new int[]{Color.RED, Color.YELLOW, Color.BLUE,
            Color.GREEN};

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            flushData();
            invalidate();

            if (!mWaveList.isEmpty()) {// 圆环集合不为空, 继续发消息绘制
                mHandler.sendEmptyMessageDelayed(0, 50);
            }
        }
    };

    public MyRingWave(Context context) {
        super(context);
    }

    public MyRingWave(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRingWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 刷新数据
    protected void flushData() {
        // 遍历圆环集合, 修改每个圆环属性
        // 需要移除的集合
        ArrayList<Wave> removeList = new ArrayList<MyRingWave.Wave>();
        for (Wave wave : mWaveList) {
            // 1. 半径变大, 2.宽度变大; 3. 颜色变淡
            wave.radius += 3;
            wave.paint.setStrokeWidth(wave.radius / 3);

            // 检查透明度是否为0, 如果是,需要移除该对象
            if (wave.paint.getAlpha() <= 0) {
                // mWaveList.remove(wave);//并发修改异常
                removeList.add(wave);
                continue;
            }

            int alpha = wave.paint.getAlpha();
            alpha -= 5;

            if (alpha < 0) {
                alpha = 0;
            }

            wave.paint.setAlpha(alpha);
        }

        mWaveList.removeAll(removeList);// 从原始集合中移除已经消失的对象
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 遍历集合, 绘制每一个圆环
        for (Wave wave : mWaveList) {
            canvas.drawCircle(wave.cx, wave.cy, wave.radius, wave.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                addPoint((int) event.getX(), (int) event.getY());
                break;

            default:
                break;
        }

        return true;
    }

    private void addPoint(int x, int y) {
        if (mWaveList.isEmpty()) {// 第一次进来
            addWave(x, y);
            mHandler.sendEmptyMessage(0);// 发消息,开始绘制圆环
        } else {
            // 先获取集合中最后一个圆
            Wave lastWave = mWaveList.get(mWaveList.size() - 1);

            // 判断将要添加的圆和最后一个圆的距离是否达到一定值
            if (Math.abs(x - lastWave.cx) > MIN_DIS
                    || Math.abs(y - lastWave.cy) > MIN_DIS) {
                addWave(x, y);
            }
        }
    }

    // 添加一个波浪
    private void addWave(int x, int y) {
        Wave wave = new Wave();
        wave.cx = x;
        wave.cy = y;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(wave.radius / 3);
        paint.setAlpha(255);// 设置透明度0-255, 255表示完全不透明

        // 设置随机颜色
        // Random random = new Random();
        // random.nextInt(4);
        int colorIndex = (int) (Math.random() * 4);
        paint.setColor(mColors[colorIndex]);

        wave.paint = paint;

        mWaveList.add(wave);
    }

    private class Wave {
        private int cx;
        private int cy;
        private int radius;
        private Paint paint;
    }

}
