package com.example.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //declare
    TextView scoreText,timeText;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5;
    ImageView imageView6,imageView7,imageView8,imageView9;
    int score = 0;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        scoreText.setText("Score : " + score);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        // ImageViewların arasında döngü yapmak için array
        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5
                                    ,imageView6,imageView7,imageView8,imageView9};

        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time : " + l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off"); // Süre yazısını değiştir
                handler.removeCallbacks(runnable);
                //Süre bittikten sonra imageleri görünmez yap
                for(ImageView image: imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure restart the game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Restart
                        Intent intent = getIntent();
                        finish(); // uygulamayı öldürüp tekrar başlatıyoruz
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }

    public void increaseScore(View view)
    {
        score++;
        scoreText.setText("Score : " + score);
    }

    public void hideImages()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}