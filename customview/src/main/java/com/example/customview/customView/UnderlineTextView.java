package com.example.customview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 创建日期：2017/9/20 on 下午6:01
 * 描述:继承系统控件的自定义View
 * 自定义View在系统控件的基础上进行拓展，一般是添加新的功能或者修改显示的效果,
 * 一般情况下我们在onDraw()方法中进行处理.
 * 作者:yangliang
 */
public class UnderlineTextView extends android.support.v7.widget.AppCompatTextView{

    //初始化画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public UnderlineTextView(Context context) {
        super(context);
        initDraw();
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public UnderlineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw() {
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth((float) 10);
    }

    /**
     * 这个自定义View继承TextView，并且在onDraw()方法中画了一条黑色的横线
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件的宽与高
        int width = getWidth();
        int height = getHeight();
        //在画布上用画笔画一条横线
        //底部
        canvas.drawLine(0,height,width,height,mPaint);
//        canvas.drawLine(0, height / 2, width, height / 2, mPaint);//删除线
    }
}
