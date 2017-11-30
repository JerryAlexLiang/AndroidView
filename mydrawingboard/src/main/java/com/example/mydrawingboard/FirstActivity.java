package com.example.mydrawingboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydrawingboard.customView.BoardView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button buttonClear = (Button) findViewById(R.id.btn_clear);
        final BoardView boardView = (BoardView) findViewById(R.id.board_view);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardView.clear();
            }
        });
    }
}
