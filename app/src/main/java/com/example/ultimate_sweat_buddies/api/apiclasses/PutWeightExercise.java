package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PutWeightExercise {
    @SerializedName("id")
    private int id;

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

    public PutWeightExercise(int id, String name, String userEmail, int sets, int reps, int weight) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
