package com.hindicoder.retrofitdemoinjava.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.fragments.DashboardFragment;
import com.hindicoder.retrofitdemoinjava.fragments.UsersFragment;
import com.hindicoder.retrofitdemoinjava.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new DashboardFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment =null;

        switch (menuItem.getItemId())
        {
            case R.id.nav_dashboard: fragment= new DashboardFragment();break;
            case R.id.nav_profile: fragment= new ProfileFragment();break;
            case R.id.nav_users: fragment= new UsersFragment();break;
        }
        if (fragment!=null)
        {
            loadFragment(fragment);
        }
        return true;
    }

    private void loadFragment(Fragment fragment)
    {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeLL,fragment).commit();
    }
}