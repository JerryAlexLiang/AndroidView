package com.example.ccscrollview.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ccscrollview.R;

public class SlideByAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_by_animation);
        Button buttonOne = (Button) findViewById(R.id.button_way_one);
        Button buttonTwo = (Button) findViewById(R.id.button_way_two);
        imageView = (ImageView) findViewById(R.id.image_view);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_way_one:
                //可以采用View动画来移动，在res目录新建anim文件夹并创建translate.xml
                imageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
                Toast.makeText(this, "hhh", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_way_two:
                //使用属性动画使view滑动
                //当然使用属性动画移动那就更简单了，让View在1000毫秒内沿着X轴像右平移300像素：
                ObjectAnimator.ofFloat(imageView, "translationX", 0, 800).setDuration(1000).start();
                ObjectAnimator.ofFloat(imageView, "translationY", 0, 800).setDuration(1000).start();
                break;

            default:
                break;
        }
    }
}
