package com.hindicoder.retrofitdemoinjava.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.apiresponse.User;
import com.hindicoder.retrofitdemoinjava.apiresponse.UserUpdateResponse;
import com.hindicoder.retrofitdemoinjava.retrofit.RetrofitClient;
import com.hindicoder.retrofitdemoinjava.shasredpreferences.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private EditText emailET, userNameET;
    private Button updateBtn;
    private EditText currentPassET, newPassET, repeatPassET;
    private Button changePassBtn;
    private SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateBtn = view.findViewById(R.id.updateUserBtn);
        emailET = view.findViewById(R.id.emailUpdateET);
        userNameET = view.findViewById(R.id.usernameUpdateET);

        changePassBtn = view.findViewById(R.id.changePasswordBtn);
        currentPassET = view.findViewById(R.id.currentPassUpdateET);
        newPassET = view.findViewById(R.id.newPassUpdateET);
        repeatPassET = view.findViewById(R.id.repeatPassUpdateET);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePass();
            }
        });
    }

    private void changePass() {
        sharedPrefManager = new SharedPrefManager(getContext());
        final String email = sharedPrefManager.getUser().getEmail();
        final String currentPass = currentPassET.getText().toString().trim();
        final String newPass = newPassET.getText().toString().trim();
        final String repeatPass = repeatPassET.getText().toString().trim();
        if (currentPass.isEmpty()) {
            currentPassET.requestFocus();
            currentPassET.setError("Current can not be blank");
            return;
        }

        if (newPass.isEmpty()) {
            newPassET.requestFocus();
            newPassET.setError("New Password can not be blank");
            return;
        }

        if (repeatPass.isEmpty()) {
            repeatPassET.requestFocus();
            repeatPassET.setError("Repeat Password can not be blank");
            return;
        }

        if (!repeatPass.equals(newPass)) {
            repeatPassET.requestFocus();
            repeatPassET.setError("Repeat Password does not match new password");
            return;
        }

        Call<UserUpdateResponse> call = RetrofitClient.getInstance().getApi().changePass(currentPass,newPass,email);
        call.enqueue(new Callback<UserUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserUpdateResponse> call, @NonNull Response<UserUpdateResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful() && response.body().getResponsecode().equals("200")) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "User update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserUpdateResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser() {
        sharedPrefManager = new SharedPrefManager(getContext());
        final int userId = sharedPrefManager.getUser().getId();
        final String userName = userNameET.getText().toString().trim();
        final String email = emailET.getText().toString().trim();

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
        if (userName.isEmpty()) {
            userNameET.requestFocus();
            userNameET.setError("User name can not be blank");
            return;
        }

        Call<UserUpdateResponse> call = RetrofitClient.getInstance().getApi().updateUser(userId, email, userName);
        call.enqueue(new Callback<UserUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserUpdateResponse> call, @NonNull Response<UserUpdateResponse> response) {
                if (response.isSuccessful() && response.body().getResponsecode().equals("200")) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    sharedPrefManager.saveUser(new User(userId, userName, email));
                } else {
                    Toast.makeText(getContext(), "User update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserUpdateResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}