package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startBtn ,dashBoardBtn ,exitBtn ;
    LinearLayout vlinearLayout2 ;
    Intent  gamePlayIntent ;
    EditText playerName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.startBtn);
        dashBoardBtn = (Button) findViewById(R.id.dashBoardBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);
        vlinearLayout2 = (LinearLayout)findViewById(R.id.vLinearLayout2);
        gamePlayIntent = new Intent(this ,GamePlay.class);
        playerName = (EditText)findViewById(R.id.playerName);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playerName.getText().length()>0){
                    startActivity(gamePlayIntent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter Player's  Name First",Toast.LENGTH_SHORT).show();
                }

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
