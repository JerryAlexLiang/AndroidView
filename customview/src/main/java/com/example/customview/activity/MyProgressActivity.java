package com.example.customview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.customview.R;
import com.example.customview.customView.MyCustomProgress;

/**
 * 创建日期：2017/11/30 on 下午8:14
 * 描述: 使用自定义View实现自定义圆形进度条的功能
 * 作者: liangyang
 */
public class MyProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCustomProgress myCustomProgress;
    private boolean isChangeStyle = false;
    private TextView progressValue;
    private int currenProgress;
    private Button buttonDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);
        myCustomProgress = (MyCustomProgress) findViewById(R.id.my_progress);

        //Java代码方式设置自定义progress的样式属性
        myCustomProgress.setRoundWidth(10);
        myCustomProgress.setRoundColor(Color.GREEN);
        myCustomProgress.setProgressColor(Color.BLUE);
        myCustomProgress.setTextColor(Color.RED);
        myCustomProgress.setTextSize(70);

        progressValue = (TextView) findViewById(R.id.get_progress_value);

        Button buttonSetStyle = (Button) findViewById(R.id.progress_style);
        buttonDownload = (Button) findViewById(R.id.start_download);

        buttonSetStyle.setOnClickListener(this);
        buttonDownload.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_download:
                buttonDownload.setEnabled(false);
                buttonDownload.setText("正在下载中...");
                //模拟下载进度
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            myCustomProgress.setProgress(i);
                            currenProgress = myCustomProgress.getProgress();
                            progressValue.postInvalidate();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressValue.setText(currenProgress + "%");
                                    if (currenProgress == 100){
                                        buttonDownload.setEnabled(true);
                                        buttonDownload.setText("开始下载");
                                    }
                                }
                            });

                            //模拟耗时操作
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;

            case R.id.progress_style:
                if (isChangeStyle) {
                    myCustomProgress.setStroke(true);
                    progressValue.setVisibility(View.GONE);
                    isChangeStyle = false;
                } else {
                    myCustomProgress.setStroke(false);
                    //设置在填充模式进度条下面显示进度
                    progressValue.setVisibility(View.VISIBLE);
                    isChangeStyle = true;
                }
                break;

            default:
                break;
        }
    }
}

