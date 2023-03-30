package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWorkoutPlanExercises {

    @SerializedName("weight_exercises")
    private List<WeightExercise> weightExercises;

    @SerializedName("endurance_exercises")
    private List<EnduranceExercise> enduranceExercises;

    public GetWorkoutPlanExercises(List<WeightExercise> weightExercises, List<EnduranceExercise> enduranceExercises) {
        this.weightExercises = weightExercises;
        this.enduranceExercises = enduranceExercises;
    }

    public List<WeightExercise> getWeightExercises() {
        return weightExercises;
    }

    public void setWeightExercises(List<WeightExercise> weightExercises) {
        this.weightExercises = weightExercises;
    }

    public List<EnduranceExercise> getEnduranceExercises() {
        return enduranceExercises;
    }

    public void setEnduranceExercises(List<EnduranceExercise> enduranceExercises) {
        this.enduranceExercises = enduranceExercises;
    }
}
