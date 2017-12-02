package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.customview.R;

public class MyClockOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clock_one);
        setTitle("自定义时钟-样式一");
    }
}
