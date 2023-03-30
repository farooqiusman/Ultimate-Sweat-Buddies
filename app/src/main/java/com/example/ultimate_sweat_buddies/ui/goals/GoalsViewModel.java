package com.example.ultimate_sweat_buddies.ui.goals;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetAllGoals;
import com.example.ultimate_sweat_buddies.data.model.BodyWeightGoals;
import com.example.ultimate_sweat_buddies.data.model.EnduranceGoals;
import com.example.ultimate_sweat_buddies.data.model.Goal;
import com.example.ultimate_sweat_buddies.data.model.MiscGoals;
import com.example.ultimate_sweat_buddies.data.model.WeightGoals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Response;

public class GoalsViewModel extends ViewModel {
    private APIInterface apiInterface;

    public GoalsViewModel(){
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }

    public CompletableFuture<List<Goal>> getGoals(String userEmail){
        Call<GetAllGoals> allGoalsCall = apiInterface.getAllGoals(userEmail);
        CompletableFuture<List<Goal>> goalFuture = CompletableFuture.supplyAsync(() -> {
            try{
                Response<GetAllGoals> response = allGoalsCall.execute();
                if (response.body() == null) return new ArrayList<>();
                List<MiscGoals> miscGoals = response.body().getMiscGoalsList();
                List<EnduranceGoals> enduranceGoals = response.body().getEnduranceGoalsList();
                List<WeightGoals> weightGoals = response.body().getWeightGoalsList();
                List<BodyWeightGoals> bodyWeightGoals = response.body().getBodyWeightGoalsList();
                List<Goal> result = new ArrayList<>();
                if (!miscGoals.isEmpty()){
                    result.addAll(miscGoals);
                }
                if (!enduranceGoals.isEmpty()){
                    result.addAll(enduranceGoals);
                }
                if (!weightGoals.isEmpty()){
                    result.addAll(weightGoals);
                }
                if (!bodyWeightGoals.isEmpty()){
                    result.addAll(bodyWeightGoals);
                }
                return result;
            }catch (IOException e){
                Log.e("exception_in_array", e.getMessage());
                return new ArrayList<>();
            }
        });
        goalFuture.thenAccept(goalFuture::complete);
        return goalFuture;
    }

}