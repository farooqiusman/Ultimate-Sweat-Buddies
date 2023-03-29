package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class WorkoutPlan {

    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("title")
    private String title;
    @SerializedName("days_of_week")
    private String daysOfWeek;
    @SerializedName("creation_date")
    private String creationDate;

    public WorkoutPlan(String userEmail, String title, String daysOfWeek, String creationDate) {
        this.userEmail = userEmail;
        this.title = title;
        this.daysOfWeek = daysOfWeek;
        this.creationDate = creationDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
