package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

public class EnduranceGoals extends Goal{
    @SerializedName("exercise_id")
    private Integer exerciseID;

    @SerializedName("time")
    private String time;
    public EnduranceGoals(Integer id, String userEmail,
                          String deadLine, Integer completed,
                          String creationDate, Integer exerciseID,
                          String time) {
        super(id, userEmail, deadLine, completed, creationDate);
        this.exerciseID = exerciseID;
        this.time = time;
    }

    public Integer getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Integer exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
