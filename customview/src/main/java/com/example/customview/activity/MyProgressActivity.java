package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.customview.R;
import com.example.customview.customView.MyCustomProgress;

/**
 * 创建日期：2017/11/30 on 下午8:14
 * 描述: 使用自定义View实现自定义圆形进度条的功能
 * 作者: liangyang
 */
public class MyProgressActivity extends AppCompatActivity {

    private MyCustomProgress myCustomProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);
        myCustomProgress = (MyCustomProgress) findViewById(R.id.my_progress);
        Button button = (Button) findViewById(R.id.start_download);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟下载进度
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 360; i++) {
                            myCustomProgress.setProgress(i);
                            //模拟耗时操作
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });


    }
}

