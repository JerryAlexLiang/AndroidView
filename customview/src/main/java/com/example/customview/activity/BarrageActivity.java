package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.customview.R;
/**
 * 创建日期：2017/12/2 on 下午11:27
 * 描述: 自定义View实现弹幕效果
 * 作者: liangyang
 */
public class BarrageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barrage);
        setTitle("弹幕");
    }
}
