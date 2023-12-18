package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterScreen extends AppCompatActivity {

    MaterialButton regButton;
    TextInputEditText regUsername, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        regUsername = (TextInputEditText) findViewById (R.id.regUsername);
        regPassword = (TextInputEditText) findViewById(R.id.regPassword);
        regButton = (MaterialButton) findViewById(R.id.regButton);

        //save user registration info
        regButton.setOnClickListener(v -> {

            try{
                //get values from user
                String username = regUsername.getEditableText().toString();
                String password = regPassword.getEditableText().toString();

                SharedPreferences prf = getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = prf.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, HomeScreen.class));
                finish();
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(), "Please Register First", Toast.LENGTH_LONG).show();
            }

        });

    }
}