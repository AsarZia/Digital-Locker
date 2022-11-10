package com.example.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.Wave;

import dmax.dialog.SpotsDialog;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tn =new Intent(SplashScreen.this,MainActivity.class);
                startActivity(tn);
                finish();
            }
        },2000);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite fadingCircle = new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);

//        Thread th = new Thread(){
//            public void run(){
//                try{
//                    sleep(5000);
//
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//                finally {
//                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//        };th.start();
    }
}

