package com.example.bilalchips.store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signupbtnbt=(Button) findViewById(R.id.signUpbtn_signupPage);
        signupbtnbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name=(EditText)findViewById(R.id.nameInput);
                EditText email=(EditText)findViewById(R.id.emailInput);
                EditText uname=(EditText)findViewById(R.id.usernameInput);
                EditText pass1=(EditText)findViewById(R.id.passwordInput);
                EditText pass2=(EditText)findViewById(R.id.cnfrmPassword);

                String namestr=name.getText().toString();
                String emailstr=email.getText().toString();
                String unamestr=uname.getText().toString();
                String pass1str=pass1.getText().toString();
                String pass2str=pass2.getText().toString();

                if(!pass1str.equals(pass2str)){
                    Toast.makeText(signUp.this, "Password dont match", Toast.LENGTH_SHORT).show();

                }
                else{
                    contact c=new contact();
                    c.setName(namestr);
                    c.setEmail(emailstr);
                    c.setUname(unamestr);
                    c.setPass(pass1str);
                    //  helper.insertbookOnArrival();
                    helper.insertContact(c);
                    Toast.makeText(signUp.this, "Your account is created", Toast.LENGTH_LONG).show();
                    //  Toast.makeText(signUp.this, "Please enter card detail", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(signUp.this, cardDetail.class));
                    startActivity(new Intent(signUp.this, loginScreen.class));
                }
            }
        });
    }
}
