package com.example.memorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GamePlay extends AppCompatActivity {


    ArrayList<Integer>arrayList = new ArrayList<Integer>();
    int[] imagesArray = {R.drawable.apple ,R.drawable.banana ,R.drawable.orange ,R.drawable.pineapple,R.drawable.apple ,R.drawable.banana ,R.drawable.orange ,R.drawable.pineapple};
    ImageView imageView ;
    int counter =0 ,selection =1 ;
    int []cardSelections = new int[2];
    ImageView [] cardSelectionsViews = new ImageView[2];
    boolean allowed =true ;
    int time = 0 ;
    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time++;
            handler.postDelayed(this,1000);
        }
    };
    MediaPlayer apple ,banana ,pineapple ,orange ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        Intent mainActivityIntentResieve = getIntent() ;
        for(int i = 0;i<8;i++){
            arrayList.add(new Integer(i));
        }
        Collections.shuffle(arrayList);
        handler.post(runnable);
        apple = MediaPlayer.create(this ,R.raw.apple);
        orange = MediaPlayer.create(this ,R.raw.orangee);
        banana = MediaPlayer.create(this ,R.raw.bananaa) ;
        pineapple = MediaPlayer.create(this ,R.raw.pineapplee);


    }
   public void Flip(View view){
        if(allowed){
            imageView = (ImageView)view ;
            //imageView.setImageResource(R.drawable.apple);
            int tappedImageView = Integer.parseInt(imageView.getTag().toString());
            //Toast.makeText(this,arrayList.get(tappedImageView).toString(),Toast.LENGTH_SHORT).show();
            imageView.setImageResource(imagesArray[arrayList.get(tappedImageView)]);
            if(selection==1){
                cardSelections[0] = imagesArray[arrayList.get(tappedImageView)];
                cardSelectionsViews[0] = imageView ;
                cardSelectionsViews[0].setEnabled(false);

                selection = 2 ;
            }
            else if(selection == 2){
                allowed = false ;
                cardSelections[1] = imagesArray[arrayList.get(tappedImageView)] ;
                cardSelectionsViews[1] = imageView ;

                if(cardSelections[0] == cardSelections[1]){
                    try{
                        if(cardSelections[0]==R.drawable.apple){
                            apple.start();
                        }
                        else if(cardSelections[0]==R.drawable.banana){
                            banana.start();
                        }
                        else if(cardSelections[0]==R.drawable.orange){
                            orange.start();
                        }
                        else if(cardSelections[0]==R.drawable.pineapple){
                            pineapple.start();
                        }

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext() ,"app was about to crash but rbna satar !!",Toast.LENGTH_SHORT).show();
                    }

                    cardSelectionsViews[0].setEnabled(false);
                    cardSelectionsViews[1].setEnabled(false);
                    allowed = true ;
                    counter ++ ;
                    if(counter == 4){
                        allowed = false ;
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Congratulations you win !!! your score is "+time+" seconds");
                        builder.setMessage("Do you want to play again ?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mainActivityIntentResieve = getIntent() ;
                                finish();
                                startActivity(mainActivityIntentResieve);
                            }
                        });
                        builder.setNegativeButton("Back to Main Menue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                       alertDialog.show();
                    }
                }
                else{
                    try {
                        final CountDownTimer countDownTimer =new CountDownTimer(1500 ,1000) {
                            @Override
                            public void onTick(long l) {
                                allowed=false ;
                            }

                            @Override
                            public void onFinish() {
                                cardSelectionsViews[0].setImageResource(R.drawable.questionmark);
                                cardSelectionsViews[1].setImageResource(R.drawable.questionmark);
                                cardSelectionsViews[0].setEnabled(true);
                                allowed = true ;
                            }
                        }.start();
                    }catch (Exception e ){
                        Toast.makeText(getApplicationContext() ,"app was about to crash but rbna satar !!",Toast.LENGTH_SHORT).show();
                    }
                }
                selection = 1 ;

            }
        }

   }

}
