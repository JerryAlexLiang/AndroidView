package com.example.customview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.customview.R;
import com.example.customview.customView.CustomGroupWidgetTitleBar;

/**
 * 创建日期：2017/9/22 on 上午11:23
 * 描述: 自定义组合控件,在主界面调用自定义的CustomGroupWidgetTitleBar，并设置了左右两遍按钮的点击事件：
 * 作者: liangyang
 */
public class ExtendsGroupWidgetActivity extends AppCompatActivity {

    private CustomGroupWidgetTitleBar customGroupWidgetTitleBar;
    private LinearLayout layoutContainer;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置不显示系统自带的TitleBar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_extends_group_widget);
        layoutContainer = (LinearLayout) findViewById(R.id.main_container);
        //初始化自定义标题栏控件
        customGroupWidgetTitleBar = (CustomGroupWidgetTitleBar) findViewById(R.id.title_bar);

        //自定义设置TitleBar的背景颜色，不设置默认为蓝色
        customGroupWidgetTitleBar.setBarBackground(Color.RED);

        //自定义设置TitleBar的标题的颜色，不设置默认为白色
        customGroupWidgetTitleBar.setBarTitleColor(Color.YELLOW);

        //自定义设置TitleBar的标题文字内容，不设置默认为"自定义组合控件"
        customGroupWidgetTitleBar.setBarTitle("自定义组合控件-TitleBar");

        //自定义设置TitleBar的左边按钮的背景，不设置则显示默认图标,设置button不显示，第一个参数设置为0，第二个参数设置为false
//        customGroupWidgetTitleBar.setLeftImageBackground(R.mipmap.ic_launcher);

        //自定义设置TitleBar的右边按钮的背景，不设置则显示默认图标,设置button不显示，第一个参数设置为0，第二个参数设置为false
//        customGroupWidgetTitleBar.setRightImageBackground(R.mipmap.ic_launcher_round, true);
//        customGroupWidgetTitleBar.setRightImageBackground(0, false);

        //自定义设置TitleBar的左边按钮的点击监听响应事件
        customGroupWidgetTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(ExtendsGroupWidgetActivity.this, "点击左键", Toast.LENGTH_SHORT).show();
            }
        });

        //自定义TitleBar的有右边按钮的点击监听响应事件
        customGroupWidgetTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    layoutContainer.setBackgroundColor(Color.GRAY);
                    //自定义设置TitleBar的左边按钮的背景
                    customGroupWidgetTitleBar.setRightImageBackground(R.drawable.ico_return, true);
//                    //设置button不显示，第一个参数设置为0，第二个参数设置为false
//                    customGroupWidgetTitleBar.setRightImageBackground(0, false);
                    System.out.println("===> " + isClicked);
                    isClicked = true;
                } else if (isClicked) {
                    layoutContainer.setBackgroundColor(Color.WHITE);
                    //自定义设置TitleBar的右边按钮的背景
                    customGroupWidgetTitleBar.setRightImageBackground(R.drawable.title_right, true);
                    System.out.println("===> " + isClicked);
                    isClicked = false;
                }
                Toast.makeText(ExtendsGroupWidgetActivity.this, "点击右键", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
