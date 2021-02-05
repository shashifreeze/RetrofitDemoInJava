package com.hindicoder.retrofitdemoinjava.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.adapters.UserAdapter;
import com.hindicoder.retrofitdemoinjava.apiresponse.FetchUserResponse;
import com.hindicoder.retrofitdemoinjava.apiresponse.User;
import com.hindicoder.retrofitdemoinjava.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.usersRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //retrofit
        Call<FetchUserResponse> call = RetrofitClient.getInstance().getApi().fetchUser();
        call.enqueue(new Callback<FetchUserResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetchUserResponse> call,@NonNull Response<FetchUserResponse> response) {

                if (response.isSuccessful())
                {
                    assert response.body() != null;
                    usersList = response.body().getUsers();
                    recyclerView.setAdapter(new UserAdapter(getContext(),usersList));
                }else{
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FetchUserResponse> call,@NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}