package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Preferences extends AppCompatActivity {

    MaterialButton changeColorButton;
    RelativeLayout layout;
    int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        changeColorButton = (MaterialButton) findViewById(R.id.changeColorButton);
        layout = findViewById(R.id.layout);
        defaultColor = ContextCompat.getColor(Preferences.this, com.google.android.material.R.color.design_default_color_primary);

        changeColorButton.setOnClickListener(v -> openColorPicker());

    }

    public void openColorPicker(){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
               defaultColor = color;
               layout.setBackgroundColor(defaultColor);
            }
        });
        ambilWarnaDialog.show();
    }


}