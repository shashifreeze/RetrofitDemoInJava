package com.hindicoder.retrofitdemoinjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hindicoder.retrofitdemoinjava.R;
import com.hindicoder.retrofitdemoinjava.apiresponse.User;

import java.util.List;
import java.util.zip.Inflater;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.userEmailTV.setText(userList.get(position).getEmail());
        holder.userNameTV.setText(userList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTV,userEmailTV;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTV=itemView.findViewById(R.id.usernameTV);
            userEmailTV=itemView.findViewById(R.id.emailTV);
        }
    }
}
