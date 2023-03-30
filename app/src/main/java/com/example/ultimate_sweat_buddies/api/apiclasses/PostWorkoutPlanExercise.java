package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PostWorkoutPlanExercise {

    @SerializedName("exercise_type")
    private String exerciseType;
    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("workout_plan_title")
    private String workoutPlanTitle;
    @SerializedName("exercise_id")
    private int exerciseId;

    public PostWorkoutPlanExercise(String exerciseType, String userEmail, String workoutPlanTitle, int exerciseId) {
        this.exerciseType = exerciseType;
        this.userEmail = userEmail;
        this.workoutPlanTitle = workoutPlanTitle;
        this.exerciseId = exerciseId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getWorkoutPlanTitle() {
        return workoutPlanTitle;
    }

    public void setWorkoutPlanTitle(String workoutPlanTitle) {
        this.workoutPlanTitle = workoutPlanTitle;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
