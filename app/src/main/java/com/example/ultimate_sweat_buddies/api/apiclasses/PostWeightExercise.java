package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PostWeightExercise {

    @SerializedName("exercise_type")
    private String exerciseType;

    @SerializedName("name")
    private String name;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("sets")
    private int sets;

    @SerializedName("reps")
    private int reps;

    @SerializedName("weight")
    private int weight;

    public PostWeightExercise(String exerciseType, String name, String userEmail, int sets, int reps, int weight) {
        this.exerciseType = exerciseType;
        this.name = name;
        this.userEmail = userEmail;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
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

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
