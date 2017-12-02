package com.example.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 创建日期：2017/12/2 on 下午11:13
 * 描述:自定义View实现飞机游戏-子弹
 * 作者:yangliang
 */
public class Bullet {

    private float x;
    public float y;

    private Bitmap bulletBitmap;

    public Bullet(Context context, float x, float y) {
        this.x = x;
        this.y = y;
        bulletBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
    }

    public void move(Canvas canvas, Paint paint) {
        y = y - 20;
        canvas.drawBitmap(bulletBitmap, x, y, paint);
    }
}
