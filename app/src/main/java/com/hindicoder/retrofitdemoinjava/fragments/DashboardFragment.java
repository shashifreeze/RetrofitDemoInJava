package com.hindicoder.retrofitdemoinjava.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.shasredpreferences.SharedPrefManager;


public class DashboardFragment extends Fragment {

    private View view;
    private TextView emailTV,usernameTV;
    private SharedPrefManager sharedPrefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        emailTV = view.findViewById(R.id.dashboard_emailTV);
        usernameTV = view.findViewById(R.id.dashboard_usernameTV);
        sharedPrefManager = new SharedPrefManager(getContext());
        String userName = "Hey! "+sharedPrefManager.getUser().getUsername();
        usernameTV.setText(userName);
        emailTV.setText(sharedPrefManager.getUser().getEmail());
        return view;
    }

}