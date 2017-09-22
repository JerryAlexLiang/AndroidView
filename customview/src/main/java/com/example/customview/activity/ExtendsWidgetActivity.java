package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.customview.R;
/**
 * 创建日期：2017/9/20 on 下午5:59
 * 描述: 继承系统控件的自定义View
 * 作者: liangyang
 */
public class ExtendsWidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("继承系统控件的自定义View");
        setContentView(R.layout.activity_extends_widget);

    }
}
