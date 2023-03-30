package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PostEnduranceExercise {

    @SerializedName("exercise_type")
    private String exerciseType;

    @SerializedName("name")
    private String name;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("time")
    private String time;

    public PostEnduranceExercise(String exerciseType, String name, String userEmail, String time) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.userEmail = userEmail;
        this.time = time;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
