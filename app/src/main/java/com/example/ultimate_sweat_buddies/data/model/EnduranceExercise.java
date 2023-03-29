package com.example.ultimate_sweat_buddies.data.model;

import java.util.Date;

public class EnduranceExercise extends Exercise {

    private String time;

    public EnduranceExercise(int id, String name, String userEmail, String time) {
        super(id, name, userEmail);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
