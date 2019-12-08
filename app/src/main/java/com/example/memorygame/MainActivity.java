package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button startBtn ,dashBoardBtn ,exitBtn ;
    LinearLayout vlinearLayout2 ;
    Intent  gamePlayIntent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.startBtn);
        dashBoardBtn = (Button) findViewById(R.id.dashBoardBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        vlinearLayout2 = (LinearLayout)findViewById(R.id.vLinearLayout2);
        gamePlayIntent = new Intent(this ,GamePlay.class);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gamePlayIntent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
