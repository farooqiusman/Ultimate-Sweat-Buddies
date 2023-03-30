package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public abstract class Goal {
    @SerializedName("id")
    private Integer id;

    @SerializedName("user_email")
    private String userEmail;

    @SerializedName("deadline")
    private String deadLine;

    @SerializedName("completed")
    private Integer completed;

    @SerializedName("creation_date")
    private String creationDate;

    public Goal(Integer id, String userEmail,
                String deadLine, Integer completed, String creationDate){
        this.id = id;
        this.userEmail = userEmail;
        this.deadLine = deadLine;
        this.completed = completed;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
