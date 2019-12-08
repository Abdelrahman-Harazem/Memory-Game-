package com.example.memorygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    int[] imagesArray = {R.drawable.apple ,R.drawable.banana ,R.drawable.orange ,R.drawable.watermelon,R.drawable.apple ,R.drawable.banana ,R.drawable.orange ,R.drawable.watermelon};
    ImageView imageView ;
    int counter =0 ,selection =1 ;
    int []cardSelections = new int[2];
    ImageView [] cardSelectionsViews = new ImageView[2];
    boolean allowed =true ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        Intent mainActivityIntentResieve = getIntent() ;
        for(int i = 0;i<8;i++){
            arrayList.add(new Integer(i));
        }
        Collections.shuffle(arrayList);


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

                selection = 2 ;
            }
            else if(selection == 2){
                allowed = false ;
                cardSelections[1] = imagesArray[arrayList.get(tappedImageView)] ;
                cardSelectionsViews[1] = imageView ;

                if(cardSelections[0] == cardSelections[1]){
                    cardSelectionsViews[0].setEnabled(false);
                    cardSelectionsViews[1].setEnabled(false);
                    allowed = true ;
                    counter ++ ;
                    if(counter == 4){
                        allowed = false ;
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Congratulations you win !!! ");
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
                        final CountDownTimer countDownTimer =new CountDownTimer(2000 ,1000) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                cardSelectionsViews[0].setImageResource(R.drawable.questionmark);
                                cardSelectionsViews[1].setImageResource(R.drawable.questionmark);
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
