package com.hindicoder.retrofitdemoinjava.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hindicoder.retrofitdemoinjava.R;

public class LoginActivity extends AppCompatActivity {

    private TextView signUpHere;
    private Button loginBtn;
    private EditText emailET,passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                finish();
            }
        });

        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    private void init() {
        loginBtn = findViewById(R.id.btnLogin);
        signUpHere = findViewById(R.id.signUpHere);
        emailET = findViewById(R.id.emailLoginET);
        passET = findViewById(R.id.passwordLoginET);
    }
}