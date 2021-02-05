package com.hindicoder.retrofitdemoinjava.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchUserResponse {
    @SerializedName("users")
    @Expose
    private List<User> users;

    @SerializedName("responsecode")
    @Expose
    private String responsecode;

    public FetchUserResponse(List<User> users, String responsecode) {
        this.users = users;
        this.responsecode = responsecode;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }
}
