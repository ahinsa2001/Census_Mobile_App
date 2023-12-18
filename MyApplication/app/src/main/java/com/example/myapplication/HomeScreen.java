package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class HomeScreen extends AppCompatActivity {

    MaterialButton preference, addData, listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        preference = (MaterialButton)findViewById(R.id.preference);
        addData = (MaterialButton)findViewById(R.id.addData);
        listData = (MaterialButton)findViewById(R.id.listData);

        preference.setOnClickListener(v -> {
            startActivity(new Intent(HomeScreen.this, Preferences.class));
//            SharedPreferences prf = getSharedPreferences("userDetails",MODE_PRIVATE);
//            prf.edit().clear().commit();
        });

        addData.setOnClickListener(v -> {
            startActivity(new Intent(HomeScreen.this, AddData.class));
        });

        listData.setOnClickListener(v -> {
           startActivity(new Intent(HomeScreen.this, ListData.class));
          //SharedPreferences prf = getSharedPreferences("DATA",MODE_PRIVATE);
         //   prf.edit().clear().commit();
        });



    }
}