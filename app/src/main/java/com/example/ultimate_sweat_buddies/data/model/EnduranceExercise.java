package com.example.ultimate_sweat_buddies.data.model;

import java.util.Date;

public class EnduranceExercise {
    private int id;
    private String exerciseName;
    private String userEmail;


    public EnduranceExercise(int id, String exerciseName, String userEmail) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getUserEmail() {
        return userEmail;
    }

}
