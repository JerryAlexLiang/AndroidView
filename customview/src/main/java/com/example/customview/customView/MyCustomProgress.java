package com.example.customview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 创建日期：2017/11/30 on 下午7:58
 * 描述:自定义Progress
 * 作者:yangliang
 */
public class MyCustomProgress extends View {

    //画笔
    private Paint paint;
    //外层颜色
    private int roundColor = Color.BLUE;
    //进度环的颜色
    private int progressColor = Color.RED;
    //圆环的宽度
    private int roundWidth = 10;
    //当前进度
    private int progress = 45;
    //设置样式(是否填充,true为stroke,false为fill)
    private boolean isStroke = true;


    public MyCustomProgress(Context context) {
        super(context);
        //初始化画笔
        initPaint();
    }

    public MyCustomProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外层大圆环
        paint.reset();
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        paint.setStyle(isStroke ? Paint.Style.STROKE : Paint.Style.FILL);
        //半径
        float radius = (Math.min(getWidth(), getHeight()) - roundWidth * 2) / 2;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);

        //画弧度
        paint.setColor(progressColor);
        paint.setAntiAlias(true
        );
        RectF oval = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
        canvas.drawArc(oval, -90, progress, !isStroke, paint);

    }

    public synchronized void setProgress(int progress) {
        System.out.println(progress);
        this.progress = progress;
        //重点：改变进度要重画（这里的写法参考Android系统的ProgressBar的写法，其可以在子线程中更新UI）
        this.postInvalidate();//post到主线程更新


    }
}
