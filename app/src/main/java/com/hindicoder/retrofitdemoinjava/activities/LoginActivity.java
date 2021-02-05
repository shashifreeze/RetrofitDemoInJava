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
import com.hindicoder.retrofitdemoinjava.retrofit.RetrofitClient;
import com.hindicoder.retrofitdemoinjava.apiresponse.LoginResponse;
import com.hindicoder.retrofitdemoinjava.shasredpreferences.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView signUpHere;
    private Button loginBtn;
    private EditText emailET,passET;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        signUpHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void userLogin() {
        String email = emailET.getText().toString();
        String password = passET.getText().toString();

        if (email.isEmpty()) {
            emailET.requestFocus();
            emailET.setError("Please enter email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.requestFocus();
            emailET.setError("Please enter correct email");
            return;
        }
        if (password.isEmpty()) {
            passET.requestFocus();
            passET.setError("Please enter password");
            return;
        }
        if (password.length() < 8) {
            passET.requestFocus();
            passET.setError("Please enter 8 char password");
            return;
        }

        Call<LoginResponse> responseCall =  RetrofitClient.getInstance().getApi().login(email,password);

        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()&&loginResponse!=null)
                {
                    int responseCode = Integer.parseInt(loginResponse.getResponsecode());
                    if (responseCode!=400)
                    {
                        sharedPrefManager.saveUser(loginResponse.getUser());
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        loginBtn = findViewById(R.id.btnLogin);
        signUpHere = findViewById(R.id.signUpHere);
        emailET = findViewById(R.id.emailLoginET);
        passET = findViewById(R.id.passwordLoginET);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sharedPrefManager.isLoggedIn())
        {
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}