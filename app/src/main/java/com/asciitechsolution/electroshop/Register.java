package com.asciitechsolution.electroshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asciitechsolution.electroshop.ui.home.HomeFragment;

public class Register extends AppCompatActivity {
Button regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regis=findViewById(R.id.regbtn);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Register.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}