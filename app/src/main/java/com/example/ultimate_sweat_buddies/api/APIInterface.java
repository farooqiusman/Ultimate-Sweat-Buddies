package com.example.ultimate_sweat_buddies.api;

import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetUserEmail;
<<<<<<< HEAD
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.util.List;
=======
import com.example.ultimate_sweat_buddies.api.apiclasses.NewUser;
>>>>>>> adf3fc9 (User login has been fully implemented)

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
<<<<<<< HEAD
import retrofit2.http.Part;
=======
import retrofit2.http.POST;
>>>>>>> adf3fc9 (User login has been fully implemented)
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/status")
    Call<GetStatus> getApiStatus();

    @GET("/user-email")
<<<<<<< HEAD
    Call<GetUserEmail> getUserEmail(@Query("user_email") String email);

    // Exercises
    @GET("/weight-exercises/{user_email}")
    Call<List<WeightExercise>> getWeightExercises(@Path("user_email") String email);

    @GET("/endurance-exercises/{user_email}")
    Call<List<EnduranceExercise>> getEnduranceExercises(@Path("user_email") String email);
=======
    Call<GetStatus> getUserEmail(@Query("user_email") String email);

    @POST("/new-user")
    Call<NewUser> newUser(@Body NewUser newUser);

    @GET("/check_auth/{user_email}/{password}")
    Call<GetStatus> checkUsrAuth(@Path("user_email") String email, @Path("password") String password);
>>>>>>> adf3fc9 (User login has been fully implemented)
}
