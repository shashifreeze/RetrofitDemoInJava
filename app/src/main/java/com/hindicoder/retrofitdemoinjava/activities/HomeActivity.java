package com.hindicoder.retrofitdemoinjava.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.apiresponse.UserUpdateResponse;
import com.hindicoder.retrofitdemoinjava.fragments.DashboardFragment;
import com.hindicoder.retrofitdemoinjava.fragments.ProfileFragment;
import com.hindicoder.retrofitdemoinjava.fragments.UsersFragment;
import com.hindicoder.retrofitdemoinjava.retrofit.RetrofitClient;
import com.hindicoder.retrofitdemoinjava.shasredpreferences.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());
        sharedPrefManager= new SharedPrefManager(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_users:
                fragment = new UsersFragment();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeLL, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_logout:
                logout();
                break;
            case R.id.menu_delete_account:
                deleteAccount();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAccount() {
        int id = sharedPrefManager.getUser().getId();
        Call<UserUpdateResponse> responseCall = RetrofitClient.getInstance().getApi().deleteAccount(id);
        responseCall.enqueue(new Callback<UserUpdateResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserUpdateResponse> call,@NonNull Response<UserUpdateResponse> response) {
                if (response.isSuccessful())
                {
                    if (response.body() != null) {
                        if (response.body().getResponsecode().equals("200"))
                        {
                            Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        Toast.makeText(HomeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(HomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserUpdateResponse> call,@NonNull Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        sharedPrefManager.logout();
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
    }
}