package com.mehmetaltindal.catchthemichelangelo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timerView;
    TextView scoreView;
    Button startButton;
    Runnable runnable;
    Handler handler;
    int number,score;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerView = findViewById(R.id.timerView);
        scoreView = findViewById(R.id.scoreView);
        imageView0 =findViewById(R.id.imageView0);
        imageView1 =findViewById(R.id.imageView1);
        imageView2 =findViewById(R.id.imageView2);
        imageView3 =findViewById(R.id.imageView3);
        imageView4 =findViewById(R.id.imageView4);
        imageView5 =findViewById(R.id.imageView5);
        imageView6 =findViewById(R.id.imageView6);
        imageView7 =findViewById(R.id.imageView7);
        imageView8 =findViewById(R.id.imageView8);
        imageArray = new ImageView[] {imageView0,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        hideImages();

        number = 20;
        new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText("Time : "+ millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                timerView.setText("Time Over!!");
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Wanna play again?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //RESTART
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Thanks for Playing :)" ,Toast.LENGTH_LONG);
                        toast.show();

                    }
                });
                alert.show();

            }
        }.start();


    }

    public void increaseScore(View view){
        score++;
        scoreView.setText("Score : "+score);

    }
    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);

    }


}