package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginScreen extends AppCompatActivity {

    TextInputEditText loginUsername,loginPassword;
    MaterialButton btn_login;
    SharedPreferences prf;
    int term = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginUsername = (TextInputEditText) findViewById(R.id.loginUsername);
        loginPassword = (TextInputEditText) findViewById(R.id.loginPassword);
        btn_login = (MaterialButton) findViewById(R.id.loginButton);


            btn_login.setOnClickListener(v -> {

                //get values from user
                String username = loginUsername.getEditableText().toString();
                String password = loginPassword.getEditableText().toString();

                //get values from Shared Preferences
                prf = getSharedPreferences("userDetails", MODE_PRIVATE);
                String realUsername = prf.getString("username", "null");
                String realPassword = prf.getString("password", "null");

                if (term < 3) {

                    if (realUsername.equals(username) && realPassword.equals(password)) {

                        startActivity(new Intent(this, HomeScreen.class));
                        finish();
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();

                    } else if (realUsername.equals(username) && !realPassword.equals(password)) {

                        term++;
                        Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_LONG).show();

                    } else if (!realUsername.equals(username)){

                        Toast.makeText(getApplicationContext(), "User not found!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    System.exit(0);
                }


            });

        }

    }
