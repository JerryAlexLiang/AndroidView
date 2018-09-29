package com.example.customview.activity;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.customview.R;
import com.example.customview.customView.LikeStarView;

/**
 * 创建日期：2017/12/17 on 下午9:49
 * 描述: 模仿直播点赞
 * 作者: liangyang
 */
public class LikeStarActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout mTouchView;
    public static final String TAG = "LikeStarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_star);
        setTitle("模仿直播点赞");
        mTouchView = (LinearLayout) findViewById(R.id.touchview);
        mTouchView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LikeStarView likeStarView = new LikeStarView(LikeStarActivity.this);
                int pointX = (int) event.getRawX();
                int pointY = (int) event.getRawY();
                likeStarView.setStartPoint(new Point(pointX, pointY));
                //添加自定义view
                ViewGroup rootView = (ViewGroup) LikeStarActivity.this.getWindow().getDecorView();
                rootView.addView(likeStarView);
                likeStarView.startAnim();
                break;
        }
        return super.onTouchEvent(event);
    }
}
