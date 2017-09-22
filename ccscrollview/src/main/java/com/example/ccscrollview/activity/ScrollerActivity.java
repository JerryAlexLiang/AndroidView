package com.example.ccscrollview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ccscrollview.R;
import com.example.ccscrollview.customView.SlideViewByScroller;

public class ScrollerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        //使用Scroll来进行平滑移动
        //这里我们是设定CustomView沿着X轴向右平移400像素。
        SlideViewByScroller customView = (SlideViewByScroller) findViewById(R.id.custom_scroller_view);
//        customView.smoothScrollTo(-400, 0);
        customView.smoothScrollTo(-400, -800);


    }
}
