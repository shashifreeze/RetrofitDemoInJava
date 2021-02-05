package com.hindicoder.retrofitdemoinjava.apiresponse;

public class LoginResponse {
    private User user;
    String  responsecode,message;

    public LoginResponse(User user, String responsecode, String message) {
        this.user = user;
        this.responsecode = responsecode;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
