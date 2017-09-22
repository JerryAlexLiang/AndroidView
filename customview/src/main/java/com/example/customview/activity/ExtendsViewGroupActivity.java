package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.customview.R;

public class ExtendsViewGroupActivity extends AppCompatActivity {

    private ListView lv_one;
    private ListView lv_two;
    private ListView lv_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extends_view_group);
        lv_one = (ListView) this.findViewById(R.id.lv_one);
        lv_two = (ListView) this.findViewById(R.id.lv_two);
        lv_three = (ListView) this.findViewById(R.id.lv_three);

        String[] strs1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs1);
        lv_one.setAdapter(adapter1);

        String[] strs2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs2);
        lv_two.setAdapter(adapter2);

        String[] strs3 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs3);
        lv_three.setAdapter(adapter3);

    }
}
