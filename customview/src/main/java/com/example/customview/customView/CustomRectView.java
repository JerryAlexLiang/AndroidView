package com.example.customview.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customview.R;

/**
 * 创建日期：2017/9/21 on 上午10:30
 * 描述:简单实现继承View的自定义View
 * 一个CustomRectView类继承View来画一个正方形
 * 作者:yangliang
 */
public class CustomRectView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mColor = Color.RED;

    public CustomRectView(Context context) {
        super(context);
        initDraw();
    }

    /**
     * 自定义属性，首先在values目录下创建 attrs.xml：
     * 这个配置文件定义了名为CustomRectView的自定义属性组合，我们定义了rect_color属性，它的格式为color，
     * 接下来在CustomRectView的构造函数中解析自定义属性的值：
     *
     * @param context
     * @param attrs
     */
    public CustomRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRectView);
        //提取CustomRectView属性集合的custom_rect_color属性，如果没有设置则为默认值Color.RED
        mColor = typedArray.getColor(R.styleable.CustomRectView_custom_rect_color, Color.RED);
        //获取资源后要及时回收
        typedArray.recycle();
        initDraw();
    }

    public CustomRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRectView);
        //提取CustomRectView属性集合的custom_rect_color属性，如果没有设置则为默认值Color.RED
        mColor = typedArray.getColor(R.styleable.CustomRectView_custom_rect_color, Color.RED);
        //获取资源后要及时回收
        typedArray.recycle();
        initDraw();
    }

    private void initDraw() {
//        paint.setColor(Color.RED);
        paint.setColor(mColor);
        paint.setStrokeWidth((float) 2.5);
    }

    /**
     * 与上面的继承系统控件的自定义View不同，继承View的自定义View实现起来要稍微复杂一些，
     * 不只是要实现onDraw()方法，而且在实现过程中还要考虑到wrap_content属性以及padding属性的设置
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int width = getWidth();
//        int height = getHeight();
//        canvas.drawRect(0, 0, width, height, paint);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
//        canvas.drawRect(0 + paddingLeft, 0 + paddingTop, width + paddingRight, height + paddingBottom, paint);
        canvas.drawRect(paddingLeft, paddingTop, width + paddingRight, height + paddingBottom, paint);

    }

    /**
     * 自定义继承View的CustomView：对wrap_content属性进行处理
     * 问题再现：不处理前，修改布局文件，让CustomRectView的宽度分别为wrap_content和match_parent效果都是一样的；
     * 解决方法：在onMeasure()方法中指定一个默认的宽和高，在设置wrap_content属性时设置此默认的宽和高就可以了；
     * 需要注意的是setMeasuredDimension()方法接收的参数的单位是px
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 400);
        }

    }
}
