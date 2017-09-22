package com.example.ccscrollview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ccscrollview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonOne = (Button) findViewById(R.id.button_one);
        Button buttonTwo = (Button) findViewById(R.id.button_two);
        Button buttonThree = (Button) findViewById(R.id.button_three);
        Button buttonFour = (Button) findViewById(R.id.button_four);
        Button buttonFive = (Button) findViewById(R.id.button_five);
        Button buttonSix = (Button) findViewById(R.id.button_six);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.button_one:
                intent.setClass(MainActivity.this, LayoutActivity.class);
                break;

            case R.id.button_two:
                intent.setClass(MainActivity.this, OffsetActivity.class);
                break;

            case R.id.button_three:
                intent.setClass(MainActivity.this, LayoutParamsActivity.class);
                break;

            case R.id.button_four:
                intent.setClass(MainActivity.this, ScollByActivity.class);
                break;

            case R.id.button_five:
                intent.setClass(MainActivity.this, ScrollerActivity.class);
                break;

            case R.id.button_six:
                intent.setClass(MainActivity.this, SlideByAnimationActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
