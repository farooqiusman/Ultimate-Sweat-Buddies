package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class PostWorkoutPlan {

    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("title")
    private String title;
    @SerializedName("days_of_week")
    private String daysOfWeek;

    public PostWorkoutPlan(String userEmail, String title, String daysOfWeek) {
        this.userEmail = userEmail;
        this.title = title;
        this.daysOfWeek = daysOfWeek;
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
}
