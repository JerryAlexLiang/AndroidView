package com.example.ccscrollview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ccscrollview.R;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ListView listView = (ListView) findViewById(R.id.list_view);
//        SlideViewByLayout slideViewByLayout = (SlideViewByLayout) findViewById(R.id.custom_layout_view);
        //初始化数据源
        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LayoutActivity.this,
                android.R.layout.simple_list_item_1, dataList);
        //绑定适配器
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add("我是数据" + (i + 1));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "我是数据" + (position + 1), Toast.LENGTH_SHORT).show();
    }
}
