package com.hindicoder.retrofitdemoinjava.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.RetrofitClient;
import com.hindicoder.retrofitdemoinjava.util.Constants;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView loginHere;
    private Button registerBtn;
    private EditText emailET,passET,nameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    private void registerUser() {
        String email = emailET.getText().toString();
        String password = passET.getText().toString();
        String name = nameET.getText().toString();

        if (name.isEmpty())
        {
            nameET.requestFocus();
            nameET.setError("Please enter username");
            return;
        }
        if (email.isEmpty())
        {
            emailET.requestFocus();
            emailET.setError("Please enter email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailET.requestFocus();
            emailET.setError("Please enter correct email");
            return;
        }
        if (password.isEmpty())
        {
            passET.requestFocus();
            passET.setError("Please enter password");
            return;
        }
        if (password.length()<8)
        {
            passET.requestFocus();
            passET.setError("Please enter 8 char password");
            return;
        }

        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(name,email,password);
        call.enqueue(new Callback<RegisterResponse>() {


            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call,@NonNull Response<RegisterResponse> response) {
                RegisterResponse registerResponse= response.body();
                if (response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, registerResponse.getResponsecode(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void init() {
        registerBtn = findViewById(R.id.btnRegister);
        loginHere = findViewById(R.id.loginHere);
        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.passwordET);
        nameET = findViewById(R.id.nameET);
    }
}