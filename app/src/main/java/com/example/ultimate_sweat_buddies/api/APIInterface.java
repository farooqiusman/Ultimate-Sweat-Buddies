package com.example.ultimate_sweat_buddies.api;

import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetUserEmail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/status")
    Call<GetStatus> getApiStatus();

    @GET("/user-email")
    Call<GetUserEmail> getUserEmail(@Body GetUserEmail body);
}
