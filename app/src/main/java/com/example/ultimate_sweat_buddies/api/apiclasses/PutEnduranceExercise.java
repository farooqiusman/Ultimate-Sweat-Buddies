package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PutEnduranceExercise {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("time")
    private String time;

    public PutEnduranceExercise(int id, String name, String userEmail, String time) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
