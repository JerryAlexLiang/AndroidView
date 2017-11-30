package com.example.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.customview.R;

import java.util.ArrayList;
import java.util.List;

public class MyPopupWindow extends AppCompatActivity {

    private EditText input;
    private ImageView downArrow;
    private List<String> msgList;
    private ListView listView;
    private PopupWindow popWin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_popup_window);
        input = (EditText) findViewById(R.id.input);
        downArrow = (ImageView) findViewById(R.id.down_arrow);

        msgList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            msgList.add("1380013800" + i);
        }

        initListView();

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("=======");
                // 定义 popupWindow
                popWin = new PopupWindow(MyPopupWindow.this);
                popWin.setWidth(input.getWidth()); // 设置宽度
                popWin.setHeight(500); // 设置popWin 高度
                //关键：为popWindow填充内容
                popWin.setContentView(listView); //
                popWin.setOutsideTouchable(true); // 点击popWin 以处的区域，自动关闭 popWin
                popWin.showAsDropDown(input, 0, 0);// 设置 弹出窗口，显示的位置
            }
        });

    }

    private void initListView() {
        listView = new ListView(this);
        listView.setBackgroundResource(R.drawable.msg_face_edit_bg); // 设置listView背景

        listView.setDivider(null); // 设置条目之间的分隔线为null
        listView.setVerticalScrollBarEnabled(false); // 关闭垂直滚动条
        listView.setAdapter(new MyListAdapter()); //绑定适配器
    }

    //定义适配器
    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return msgList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.list_item, null);
                holder = new ViewHolder();
                holder.delete = (ImageView) convertView.findViewById(R.id.delete);
                holder.tv_msg = (TextView) convertView.findViewById(R.id.tv_list_item);
                convertView.setTag(holder);
            } else {
                //重新加载
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_msg.setText(msgList.get(position));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 删除对应的条目
                    msgList.remove(position);
                    // 刷新listView
                    MyListAdapter.this.notifyDataSetChanged();
                }
            });

            //删除事件
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 设置输入框
                    input.setText(msgList.get(position));
                    popWin.dismiss();
                }
            });

            return convertView;
        }
    }

    private class ViewHolder {
        TextView tv_msg;
        ImageView delete;
    }
}
