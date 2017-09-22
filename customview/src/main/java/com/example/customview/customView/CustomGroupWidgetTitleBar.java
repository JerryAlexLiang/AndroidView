package com.example.customview.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.customview.R;

import static com.example.customview.R.drawable.ico_return;
import static com.example.customview.R.drawable.title_right;

/**
 * 创建日期：2017/9/21 on 下午6:12
 * 描述:自定义组合控件的Java代码
 * 步骤：
 * 1、创建自定义组合控件XML布局
 * 2、编写组合控件的Java代码
 * 3、重写了三个构造方法并在构造方法中加载布局文件
 * 4、对外提供了三个方法，分别用来设置标题的名字，和左右按钮的点击事件。
 * 5、自定义属性，同样的我们在values目录下创建 attrs.xml：分别用来设置顶部标题栏的背景颜色、标题文字颜色和标题文字
 * 6、为了引入自定义属性，需要在TitleBar的构造函数中解析自定义属性的值
 * 7、xml中引用组合控件
 * 8、在主界面调用组合控件
 * 注意：因为我们的组合控件整体布局是RelativeLayout，所以我们的组合控件要继承RelativeLayout：<p>
 * 作者:yangliang
 */
public class CustomGroupWidgetTitleBar extends RelativeLayout {

    private ImageView iv_title_bar_left;
    private ImageView iv_title_bar_right;
    private TextView tv_title_bar_title;
    private RelativeLayout rl_title_bar_root_layout;
    //    private int mColor = Color.BLUE;
    private int mColor = Color.parseColor("#303F9F");//colorPrimary
    private int mTextColor = Color.WHITE;
    private String titleName;

    public CustomGroupWidgetTitleBar(Context context) {
        super(context);
        //初始化控件
        initView(context);
    }

    public CustomGroupWidgetTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomGroupWidgetTitleBar);
//        mColor = typedArray.getColor(R.styleable.CustomGroupWidgetTitleBar_title_bar_background, mColor);
//        mTextColor = typedArray.getColor(R.styleable.CustomGroupWidgetTitleBar_title_text_color, Color.WHITE);
//        titleName = typedArray.getString(R.styleable.CustomGroupWidgetTitleBar_title_text_content);
//        //获取资源后，要及时回收资源
//        typedArray.recycle();

        //在CustomGroupWidgetTitleBar的构造函数中解析自定义属性的值
        initTypedArray(context, attrs);
        //初始化控件
        initView(context);
    }

    public CustomGroupWidgetTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //解析自定义属性的值
        initTypedArray(context, attrs);
        //初始化控件
        initView(context);
    }

    /**
     * 在CustomGroupWidgetTitleBar的构造函数中解析自定义属性的值
     *
     * @param context
     * @param attrs
     */
    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomGroupWidgetTitleBar);
        mColor = typedArray.getColor(R.styleable.CustomGroupWidgetTitleBar_title_bar_background, mColor);
        mTextColor = typedArray.getColor(R.styleable.CustomGroupWidgetTitleBar_title_text_color, mTextColor);
        titleName = typedArray.getString(R.styleable.CustomGroupWidgetTitleBar_title_text_content);
        //获取资源后，要及时回收资源
        typedArray.recycle();
    }

    /**
     * 初始化视图，加载布局文件
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_group_title_bar, this, true);
        iv_title_bar_left = (ImageView) findViewById(R.id.iv_title_bar_left);
        iv_title_bar_right = (ImageView) findViewById(R.id.iv_title_bar_right);
        tv_title_bar_title = (TextView) findViewById(R.id.tv_title_bar_title);
        rl_title_bar_root_layout = (RelativeLayout) findViewById(R.id.title_bar_root_layout);

        //设置左边button控件的背景,设置button不显示，第一个参数设置为0，第二个参数设置为false
//        iv_title_bar_left.setImageResource(ico_return);
        setLeftImageBackground(ico_return, true);
        //设置右边button控件的背景,设置button不显示，第一个参数设置为0，第二个参数设置为false
//        iv_title_bar_right.setImageResource(title_right);
        setRightImageBackground(title_right, true);
        //设置背景颜色
//        rl_title_bar_root_layout.setBackgroundColor(mColor);
        setBarBackground(mColor);
        //设置标题文字颜色
//        tv_title_bar_title.setTextColor(mTextColor);
        setBarTitleColor(mTextColor);
        //设置标题栏文字内容
        setBarTitle(titleName);
    }

    /**
     * 设置右边button控件的背景
     *
     * @param title_right
     */
    public void setRightImageBackground(int title_right, boolean rightButtonVisible) {
        if (rightButtonVisible) {
            iv_title_bar_right.setVisibility(VISIBLE);
            iv_title_bar_right.setImageResource(title_right);
        } else {
            iv_title_bar_right.setVisibility(GONE);
        }

    }

    /**
     * 设置左边button控件的背景
     *
     * @param ico_return
     */
    public void setLeftImageBackground(int ico_return, boolean rightButtonVisible) {
        if (rightButtonVisible) {
            iv_title_bar_left.setVisibility(VISIBLE);
            iv_title_bar_left.setImageResource(ico_return);
        } else {
            iv_title_bar_left.setVisibility(GONE);
        }
    }


    /**
     * 设置标题栏字体颜色
     *
     * @param mTextColor
     */
    public void setBarTitleColor(int mTextColor) {
        tv_title_bar_title.setTextColor(mTextColor);
    }

    /**
     * 设置标题栏背景颜色，以供外界调用
     *
     * @param mColor
     */
    public void setBarBackground(int mColor) {
        rl_title_bar_root_layout.setBackgroundColor(mColor);
    }

    /**
     * 设置标题栏文字内容
     *
     * @param titleName
     */
    public void setBarTitle(String titleName) {
        if (!TextUtils.isEmpty(titleName)) {
            tv_title_bar_title.setText(titleName);
        }
    }

    /**
     * 标题栏左侧按钮点击监听响应事件
     *
     * @param onClickListener
     */
    public void setLeftListener(OnClickListener onClickListener) {
        iv_title_bar_left.setOnClickListener(onClickListener);
    }

    /**
     * 标题栏右侧按钮点击监听响应事件
     *
     * @param onClickListener
     */
    public void setRightListener(OnClickListener onClickListener) {
        iv_title_bar_right.setOnClickListener(onClickListener);
    }

}
