package com.example.ultimate_sweat_buddies.ui.plans;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Response;

public class PlansViewModel extends ViewModel {

    private APIInterface apiInterface;

    public PlansViewModel() {
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
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
}