package com.example.ultimate_sweat_buddies.data.model;

import java.util.List;

public class Exercises {

    private String userEmail;
    private String workoutPlanTitle;
    private int exerciseId;
    private String exerciseType;
    private List<EnduranceExercise> enduranceExercises;
    private List<WeightExercise> weightExercises;


    public Exercises(String userEmail, String workoutPlanTitle, Integer exerciseId, String exerciseType, List<EnduranceExercise> enduranceExercises, List<WeightExercise> weightExercises) {
        this.userEmail = userEmail;
        this.workoutPlanTitle = workoutPlanTitle;
        this.exerciseId = exerciseId;
        this.exerciseType = exerciseType;
        this.enduranceExercises = enduranceExercises;
        this.weightExercises = weightExercises;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getWorkoutPlanTitle() {
        return workoutPlanTitle;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public List<EnduranceExercise> getEnduranceExercises() {
        return enduranceExercises;
    }

    public List<WeightExercise> getWeightExercises() {
        return weightExercises;
    }

}
