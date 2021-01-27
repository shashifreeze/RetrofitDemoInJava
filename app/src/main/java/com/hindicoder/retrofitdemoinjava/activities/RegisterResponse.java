package com.hindicoder.retrofitdemoinjava.activities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("responsecode")
    @Expose
    private String responsecode;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterResponse() {
    }

    /**
     *
     * @param responsecode
     * @param message
     */
    public RegisterResponse(String responsecode, String message) {
        super();
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
