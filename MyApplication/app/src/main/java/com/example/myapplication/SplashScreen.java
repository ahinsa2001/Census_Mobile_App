package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prf = getSharedPreferences("userDetails", MODE_PRIVATE);


        if(prf.contains("username")){
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, LoginScreen.class));
            }, 4000);
        }
        else {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreen.this, RegisterScreen.class));
            }, 4000);
        }

    }

}