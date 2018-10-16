package com.example.bilalchips.store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class welcome_screen extends AppCompatActivity {
private TextView textView;
private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        textView=(TextView)findViewById(R.id.textSplash);
        imageView=(ImageView)findViewById(R.id.imageSplash);

        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.welcome_transition);
        textView.startAnimation(myanim);
        imageView.startAnimation(myanim);


        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(welcome_screen.this,loginScreen.class));
                    finish();
                }
            }

        };
        timer.start();

    }
}
