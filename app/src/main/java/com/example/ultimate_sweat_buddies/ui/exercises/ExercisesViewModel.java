package com.example.ultimate_sweat_buddies.ui.exercises;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostEnduranceExercise;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWeightExercise;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExercisesViewModel extends ViewModel {

    private final APIInterface apiInterface;


    public ExercisesViewModel() {
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }

    public CompletableFuture<List<Exercise>> getExercises(String userEmail) {

        Call<List<WeightExercise>> weightCall = apiInterface.getWeightExercises(userEmail);
        Call<List<EnduranceExercise>> enduranceCall = apiInterface.getEnduranceExercises(userEmail);

        CompletableFuture<List<WeightExercise>> weightFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<WeightExercise>> weightResponse = weightCall.execute();
                List<WeightExercise> weightData = weightResponse.body();
                if (weightData != null) {
                    return new ArrayList<>(weightData);
                } else {
                    return new ArrayList<>();
                }
            } catch (IOException e) {
                Log.e("get_weight_exercises", e.getMessage());
                return new ArrayList<>();
            }
        });

        CompletableFuture<List<EnduranceExercise>> enduranceFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<EnduranceExercise>> enduranceResponse = enduranceCall.execute();
                List<EnduranceExercise> enduranceData = enduranceResponse.body();
                if (enduranceData != null) {
                    return new ArrayList<>(enduranceData);
                } else {
                    return new ArrayList<>();
                }
            } catch (IOException e) {
                Log.e("get_endurance_exercises", e.getMessage());
                return new ArrayList<>();
            }
        });

        // Combine the results of both futures
        CompletableFuture<List<Exercise>> combinedFuture = weightFuture.thenCombine(enduranceFuture, (weightData, enduranceData) -> {
            List<Exercise> data = new ArrayList<>();
            data.addAll(weightData);
            data.addAll(enduranceData);
            return data;
        });

        // Complete the future with the combined results
        combinedFuture.thenAccept(combinedFuture::complete);

        return combinedFuture;
    }

    //post weight exercise
    public CompletableFuture<Boolean> postWeightExercises(PostWeightExercise postWeightExercise) {

        Call<PostWeightExercise> call = apiInterface.postWeightExercise(postWeightExercise);
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
           try {
               Response<PostWeightExercise> response = call.execute();
               future.complete(response.isSuccessful());
           } catch (IOException e){
               Log.e("post" , e.getMessage());
           }
        });

        return future;

    }

    //Post Endurance Exercise
    public CompletableFuture<Boolean> postEnduranceExercises(PostEnduranceExercise postEnduranceExercise){

        Call<PostEnduranceExercise> call = apiInterface.postEnduranceExercise(postEnduranceExercise);
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                Response<PostEnduranceExercise> response = call.execute();
                future.complete(response.isSuccessful());
            } catch (IOException e){
                Log.e("post" , e.getMessage());
            }
        });

        return future;
    }

}