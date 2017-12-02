package com.example.customview.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private int currentProgress;
    private Button buttonDownload;
    private Button buttonSetStyle;
    private EditText etSetValue;
    private Button btnSetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);

        setTitle("自定义圆形进度条");

        //初始化控件
        initView();

        //设置点击监听事件
        buttonSetStyle.setOnClickListener(this);
        buttonDownload.setOnClickListener(this);
        btnSetValue.setOnClickListener(this);


    }

    private void initView() {
        myCustomProgress = (MyCustomProgress) findViewById(R.id.my_progress);

//        //Java代码方式设置自定义progress的样式属性
//        myCustomProgress.setRoundWidth(10);
//        myCustomProgress.setRoundColor(Color.GREEN);
//        myCustomProgress.setProgressColor(Color.BLUE);
//        myCustomProgress.setTextColor(Color.RED);
//        myCustomProgress.setTextSize(70);

        progressValue = (TextView) findViewById(R.id.get_progress_value);

        etSetValue = (EditText) findViewById(R.id.et_set_value);
        btnSetValue = (Button) findViewById(R.id.btn_set_value);

        buttonSetStyle = (Button) findViewById(R.id.progress_style);
        buttonDownload = (Button) findViewById(R.id.start_download);
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
                            currentProgress = myCustomProgress.getProgress();
                            progressValue.postInvalidate();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressValue.setText(currentProgress + "%");
                                    if (currentProgress == 100) {
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
                setStyle();
                break;

            case R.id.btn_set_value:
                String setValue = etSetValue.getText().toString().trim();
                if (setValue.equals("")) {
                    Toast.makeText(this, "输入不能为空，请重新输入！", Toast.LENGTH_SHORT).show();
                } else {
                    int customValueInteger = Integer.parseInt(setValue);

                    setStyle();

                    if (customValueInteger >= 0 && customValueInteger <= 100) {
                        myCustomProgress.setProgress(customValueInteger);
                        progressValue.postInvalidate();
                        progressValue.setText(customValueInteger + "%");
                    } else {
                        Toast.makeText(this, "请输入0-100之间的数字！", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            default:
                break;
        }
    }

    private void setStyle() {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.java_set:
                //Java代码方式设置自定义progress的样式属性
                myCustomProgress.setRoundWidth(10);
                myCustomProgress.setRoundColor(Color.GREEN);
                myCustomProgress.setProgressColor(Color.BLUE);
                myCustomProgress.setTextColor(Color.RED);
                myCustomProgress.setTextSize(70);
                break;

            case R.id.xml_set:
                Intent intent = new Intent(MyProgressActivity.this, MyProgressActivity2.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}

