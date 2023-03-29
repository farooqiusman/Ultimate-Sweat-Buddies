package com.example.ultimate_sweat_buddies.api;

import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetUserEmail;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/status")
    Call<GetStatus> getApiStatus();

    @GET("/user-email")
    Call<GetUserEmail> getUserEmail(@Query("user_email") String email);

    // Exercises
    @GET("/weight-exercises/{user_email}")
    Call<List<WeightExercise>> getWeightExercises(@Path("user_email") String email);

    @GET("/endurance-exercises/{user_email}")
    Call<List<EnduranceExercise>> getEnduranceExercises(@Path("user_email") String email);
}
