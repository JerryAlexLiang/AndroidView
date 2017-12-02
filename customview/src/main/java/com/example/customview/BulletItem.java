package com.example.customview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 创建日期：2017/12/2 on 下午11:31
 * 描述:弹幕-文字
 * 作者:yangliang
 */
public class BulletItem {

    public int x;
    public int baseline;
    private int count;

    public BulletItem(int x, int baseline, int count) {
        this.x = x;
        this.baseline = baseline;
        this.count = count;
    }

    public void move(Canvas canvas, Paint paint) {
        x -= 20;
        paint.setTextSize(30);
        canvas.drawText("Android： " + count, x, baseline, paint);
//            canvas.drawText();
    }
}
