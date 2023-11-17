package com.example.firstapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users/1")
    Call<UserData> getUserData();
}