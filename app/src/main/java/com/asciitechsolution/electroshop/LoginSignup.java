package com.asciitechsolution.electroshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class LoginSignup extends AppCompatActivity {
    Button login,regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        login=findViewById(R.id.sign_loginLOGIN);
        regis=findViewById(R.id.sign_loginSIGNUP);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginSignup.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        regis.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    regis.setBackgroundColor(Color.RED);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    regis.setBackgroundColor(Color.BLUE);
                }
                return false;
            }



        });
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginSignup.this,Register.class);
                startActivity(i);
                finish();

            }
        });
    }
}