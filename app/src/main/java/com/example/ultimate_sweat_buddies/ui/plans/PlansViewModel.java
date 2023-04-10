package com.example.ultimate_sweat_buddies.ui.plans;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetWorkoutPlanExercises;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWorkoutPlan;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWorkoutPlanExercise;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

public class PlansViewModel extends ViewModel {

    private final APIInterface apiInterface;

    public PlansViewModel() {
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }
    public boolean validateInput(String title, String daysOfWeek) {
        return !title.isEmpty() && !daysOfWeek.isEmpty();
    }

    public CompletableFuture<List<WorkoutPlan>> getPlans(String userEmail) {

        Call<List<WorkoutPlan>> call = apiInterface.getWorkoutPlans(userEmail);

        CompletableFuture<List<WorkoutPlan>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<WorkoutPlan>> response = call.execute();
                List<WorkoutPlan> plansData = response.body();
                if (plansData != null) {
                    return new ArrayList<>(plansData);
                } else {
                    return new ArrayList<>();
                }
            } catch (IOException e) {
                Log.e("get_plans", e.getMessage());
                return new ArrayList<>();
            }
        });

        future.thenAccept(future::complete);

        return future;
    }

    public CompletableFuture<Boolean> postPlan(String userEmail, String title, String daysOfWeek, List<Exercise> planExercises) {
        Call<PostWorkoutPlan> call = apiInterface.postWorkoutPlan(new PostWorkoutPlan(userEmail, title, daysOfWeek));

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                boolean allSucceeded = true;
                Response<PostWorkoutPlan> response = call.execute();
                // If the workout plan is created, create all of the workout plan exercise records
                if (response.isSuccessful()) {
                    for (Exercise ex : planExercises) {
                        String exerciseType = ex instanceof WeightExercise ? "weight" : "endurance";
                        boolean success = postPlanExercise(exerciseType, userEmail, title, ex.getId()).get();
                        if (!success) {
                            allSucceeded = false;
                            Log.e("post_workout_plan_exercise_error", "Could not create workout plan exercise for plan: " + title);
                        }
                    }
                } else {
                    allSucceeded = false;
                }
                future.complete(allSucceeded);
            } catch (IOException e) {
                Log.e("delete_plan", e.getMessage());
                future.complete(false);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return future;
    }

    public CompletableFuture<Boolean> updatePlan(
            String userEmail, String existingTitle, WorkoutPlan updatedPlan, List<Exercise> workoutPlanExercises
    ) {
        Call<WorkoutPlan> call = apiInterface.putWorkoutPlan(userEmail, existingTitle, updatedPlan);

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                boolean allSucceeded = true;
                Response<WorkoutPlan> response = call.execute();
                // If the workout plan is created, create all of the workout plan exercise records
                if (response.isSuccessful()) {
                    // First remove all exercises from the plan
                    boolean success = deleteWorkoutPlanExercises(userEmail, updatedPlan.getTitle()).get();
                    if (!success) {
                        Log.e("delete_workout_plan_exercises_error",
                                "Could not delete workout plan exercises for plan: " + updatedPlan.getTitle());
                        future.complete(false);
                        return;
                    }

                    // Then add the exercises to the plan
                    for (Exercise ex : workoutPlanExercises) {
                        String exerciseType = ex instanceof WeightExercise ? "weight" : "endurance";
                        success = postPlanExercise(exerciseType, userEmail, updatedPlan.getTitle(), ex.getId()).get();
                        if (!success) {
                            allSucceeded = false;
                            Log.e("post_workout_plan_exercise_error",
                                    "Could not create workout plan exercise for plan: " + updatedPlan.getTitle());
                        }
                    }
                } else {
                    allSucceeded = false;
                }
                future.complete(allSucceeded);
            } catch (IOException | ExecutionException | InterruptedException e) {
                Log.e("delete_plan", e.getMessage());
                future.complete(false);
            }
        });

        return future;
    }

    public CompletableFuture<Boolean> deleteWorkoutPlanExercises(String userEmail, String workoutPlanTitle) {
        Call<Void> call = apiInterface.deleteWorkoutPlanExercises(userEmail, workoutPlanTitle);

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                Response<Void> response = call.execute();
                future.complete(response.isSuccessful());
            } catch (IOException e) {
                Log.e("delete_plan_exercises", e.getMessage());
                future.complete(false);
            }
        });

        return future;
    }

    public CompletableFuture<Boolean> postPlanExercise(String exerciseType, String userEmail, String workoutPlanTitle, int exerciseId) {
        PostWorkoutPlanExercise payload = new PostWorkoutPlanExercise(exerciseType, userEmail, workoutPlanTitle, exerciseId);
        Call<PostWorkoutPlanExercise> call = apiInterface.postWorkoutPlanExercise(payload);

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                Response<PostWorkoutPlanExercise> response = call.execute();
                future.complete(response.isSuccessful());
            } catch (IOException e) {
                Log.e("post_plan_exercise", e.getMessage());
                future.complete(false);
            }
        });

        return future;
    }

    public CompletableFuture<Boolean> deletePlan(String userEmail, String title) {

        Call<Void> call = apiInterface.deleteWorkoutPlan(userEmail, title);

        CompletableFuture<Boolean> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {
                Response<Void> response = call.execute();
                future.complete(response.isSuccessful());
            } catch (IOException e) {
                Log.e("delete_plan", e.getMessage());
                future.complete(false);
            }
        });

        return future;
    }

    public CompletableFuture<List<Exercise>> getWorkoutPlanExercises(String userEmail, String title) {

        Call<GetWorkoutPlanExercises> call = apiInterface.getWorkoutPlanExercises(userEmail, title);

        CompletableFuture<List<Exercise>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<GetWorkoutPlanExercises> response = call.execute();
                if (response.body() == null) return new ArrayList<>();

                List<WeightExercise> weightExercises = response.body().getWeightExercises();
                List<EnduranceExercise> enduranceExercises = response.body().getEnduranceExercises();
                List<Exercise> result = new ArrayList<>();

                if (!weightExercises.isEmpty()) {
                    result.addAll(weightExercises);
                }
                if (!enduranceExercises.isEmpty()) {
                    result.addAll(enduranceExercises);
                }

                return result;
            } catch (IOException e) {
                Log.e("get_weight_exercises", e.getMessage());
                return new ArrayList<>();
            }
        });

        // Complete the future with the combined results
        future.thenAccept(future::complete);

        return future;
    }
}