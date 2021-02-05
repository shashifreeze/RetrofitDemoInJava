package com.hindicoder.retrofitdemoinjava.apiresponse;

//The response is used for change password, update profile, and delete account


public class UserUpdateResponse {
    String  responsecode,message;

    public UserUpdateResponse(String responsecode, String message) {
        this.responsecode = responsecode;
        this.message = message;
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
