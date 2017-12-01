package com.example.customview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Paint.Style.STROKE;

/**
 * 创建日期：2017/11/30 on 下午7:58
 * 描述:自定义Progress(默认设置为空心描边进度条)
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
    private int roundWidth = 20;
    //当前进度
    private int progress = 0;
    //最大进度值
    private int maxProgress = 100;
    //设置样式(是否填充,true为stroke,false为fill)
    private boolean isStroke = true;
    //圆环中是否显示文字（当外环样式为stroke时显示文字）
    private boolean isTextDisplay = true;
    //文字的颜色
    private int textColor = Color.BLUE;
    //文字的大小
    private int textSize = 75;


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
        //isStroke : true,则Style.STROKE 填充样式改为描边,false:则Style.FILL 填充样式改为填充
        paint.setStyle(isStroke ? STROKE : Paint.Style.FILL);
        //半径
        //int center = getWidth() / 2;// 得到圆心的x坐标
        float radius = (Math.min(getWidth(), getHeight()) - roundWidth * 2) / 2;
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);

        //画弧度
        paint.setColor(progressColor);
        paint.setAntiAlias(true);
        RectF oval = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
//        canvas.drawArc(oval, -90, progress, !isStroke, paint);
        //useCenter:是否有弧的两边，True，有两边，False，只有一条弧
        canvas.drawArc(oval, -90, 360 * progress / maxProgress, !isStroke, paint);

        //在圆心画文字(进度值)
        paint.reset();
        paint.setStrokeWidth(0);
        paint.setAntiAlias(true);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        // 中间的进度百分比，先转换成float在进行除法运算，不然都为0
        int percent = (int) (((float) progress / (float) maxProgress) * 100);
        float textWidth = paint.measureText(percent + "%");// 通过画笔测量文字的宽度(重点)
        float paintOffset = paint.descent() + paint.ascent();//画笔偏移量
        if (isTextDisplay && percent != 0 && isStroke) {
//            canvas.drawText(percent + "%", getWidth() / 2 - textWidth / 2, getHeight() / 2 + textWidth / 3, paint);
            //bug修改，使进度文字居中
            canvas.drawText(percent + "%", (getWidth() - textWidth) / 2, (getWidth() - paintOffset) / 2, paint);
        }
        if (isTextDisplay && percent == 100) {
            canvas.drawText("完成", getWidth() / 2 - textWidth / 2, (getHeight() / 4) * 3, paint);
        }

    }

    public synchronized void setProgress(int progress) {
        System.out.println(progress);
        this.progress = progress;
        //重点：改变进度要重画（这里的写法参考Android系统的ProgressBar的写法，其可以在子线程中更新UI）
        this.postInvalidate();//post到主线程更新
    }

    //封装属性，对外提供
    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public int getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(int roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public boolean isStroke() {
        return isStroke;
    }

    public void setStroke(boolean stroke) {
        isStroke = stroke;
    }

    public boolean isTextDisplay() {
        return isTextDisplay;
    }

    public void setTextDisplay(boolean textDisplay) {
        isTextDisplay = textDisplay;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
