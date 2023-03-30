package com.example.ultimate_sweat_buddies.api;

import com.example.ultimate_sweat_buddies.api.apiclasses.GetAllGoals;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetWorkoutPlanExercises;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWorkoutPlan;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWorkoutPlanExercise;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostEnduranceExercise;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWeightExercise;

import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;

import java.util.List;
import com.example.ultimate_sweat_buddies.api.apiclasses.NewUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    // DB Status
    @GET("/status")
    Call<GetStatus> getApiStatus();

    // Authorization
    @GET("/user-email")
    Call<GetStatus> getUserEmail(@Query("user_email") String email);

    @POST("/new-user")
    Call<NewUser> newUser(@Body NewUser newUser);



    // Exercises
    @GET("/weight-exercises/{user_email}")
    Call<List<WeightExercise>> getWeightExercises(@Path("user_email") String email);

    @GET("/endurance-exercises/{user_email}")
    Call<List<EnduranceExercise>> getEnduranceExercises(@Path("user_email") String email);

    @POST("/exercises")
    Call<PostWeightExercise> postWeightExercise(@Body PostWeightExercise postWeightExercise);

    @POST("/exercises")
    Call<PostEnduranceExercise> postEnduranceExercise(@Body PostEnduranceExercise postEnduranceExercise);


    @DELETE("/exercises/{exercise_type}/{id}")
    Call<Void> deleteExercise(@Path("id") Integer id, @Path("exercise_type") String exerciseType);



    // Plans
    @GET("/plans/{user_email}")
    Call<List<WorkoutPlan>> getWorkoutPlans(@Path("user_email") String email);

    @POST("/plans")
    Call<PostWorkoutPlan> postWorkoutPlan(@Body PostWorkoutPlan plan);

    @DELETE("/plans/{user_email}/{title}")
    Call<Void> deleteWorkoutPlan(@Path("user_email") String userEmail, @Path("title") String title);

    // Workout Plan Exercises
    @GET("/workout-plan-exercises/{user_email}/{title}")
    Call<GetWorkoutPlanExercises> getWorkoutPlanExercises(@Path("user_email") String email, @Path("title") String title);

    @POST("/workout-plan-exercises")
    Call<PostWorkoutPlanExercise> postWorkoutPlanExercise(@Body PostWorkoutPlanExercise body);

    @GET("/check_auth/{user_email}/{password}")
    Call<GetStatus> checkUsrAuth(@Path("user_email") String email, @Path("password") String password);

    @GET("/goals/{user_email}")
    Call<GetAllGoals> getAllGoals(@Path("user_email") String email);

}
