package com.example.bilalchips.store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class loginScreen extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);

    public static int customer_id;
    Animation uptodown,downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        LinearLayout l1=(LinearLayout) findViewById(R.id.l1);
        LinearLayout l2=(LinearLayout) findViewById(R.id.l2);
        Button loginbtn=(Button)findViewById(R.id.loginbtn);
        Button signupbtn=(Button)findViewById(R.id.signUpbtn);

        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);

        downtoup= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(loginScreen.this, signUp.class));//actual one
               // startActivity(new Intent(loginScreen.this, menuoption.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, menuoption.class));

                EditText a = (EditText) findViewById(R.id.TFusername);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.TFpassword);
                String pass = b.getText().toString();


                String password = helper.searchPass(str);
                customer_id=helper.currentuserid(str);


                if(pass.equals(password)){

                   startActivity(new Intent(loginScreen.this, Home.class));

                }

                else {
                    Toast.makeText(loginScreen.this, "Username and password dont match", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
