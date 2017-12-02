package com.example.customview.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.customview.Bullet;
import com.example.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2017/12/2 on 下午11:12
 * 描述:自定义View实现飞机游戏
 * 作者:yangliang
 */
public class MyPlaneView extends View {

    private float planeX;
    private float planeY;
    private Paint mPaint;
    private Bitmap myPlane;

    private Context mContext;

    private int clock;

    private List<Bullet> bullets = new ArrayList<>();

    public MyPlaneView(Context context) {
        this(context, null);
    }

    public MyPlaneView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPlaneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //新建画笔
        mPaint = new Paint();
        //初始化飞机的图片
        myPlane = BitmapFactory.decodeResource(context.getResources(), R.drawable.myplane);

        mContext = context;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        planeX = event.getRawX();
        planeY = event.getRawY();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (clock % 5 == 0) {
            Bullet bullet = new Bullet(mContext, planeX - 40, planeY - 200);
            bullets.add(bullet);
        }


        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(canvas, mPaint);

            if (bullets.get(i).y <= 10) {
                bullets.remove(i);
            }
        }
        canvas.drawBitmap(myPlane, planeX - 66, planeY - 200, mPaint);

        clock++;
        invalidate();
    }
}
