package com.hindicoder.retrofitdemoinjava.api;

import com.hindicoder.retrofitdemoinjava.apiresponse.FetchUserResponse;
import com.hindicoder.retrofitdemoinjava.apiresponse.LoginResponse;
import com.hindicoder.retrofitdemoinjava.apiresponse.RegisterResponse;
import com.hindicoder.retrofitdemoinjava.apiresponse.UserUpdateResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("signup.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("userupdate.php")
    Call<UserUpdateResponse> updateUser(
            @Field("id") int userId,
            @Field("email") String email,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("updatepassword.php")
    Call<UserUpdateResponse> changePass(
            @Field("current") String currentPass,
            @Field("new") String newPass,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("deleteaccount.php")
    Call<UserUpdateResponse> deleteAccount(@Field("id") int id);


    @POST("fetchuser.php")
    Call<FetchUserResponse> fetchUser();
}
