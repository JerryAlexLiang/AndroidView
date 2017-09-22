package com.example.customview.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.customview.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(MainActivity.this, ExtendsWidgetActivity.class);
                break;

            case 1:
                intent.setClass(MainActivity.this, ExtendsViewActivity.class);
                break;

            case 2:
                intent.setClass(MainActivity.this, ExtendsGroupWidgetActivity.class);
                break;

            default:
                break;

        }
        startActivity(intent);
    }
}
