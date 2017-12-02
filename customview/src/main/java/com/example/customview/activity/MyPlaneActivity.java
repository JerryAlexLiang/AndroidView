package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.customview.R;
/**
 * 创建日期：2017/12/2 on 下午11:10
 * 描述: 自定义View实现飞机游戏
 * 作者: liangyang
 */
public class MyPlaneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plane);
        setTitle("飞机大战");
    }
}
