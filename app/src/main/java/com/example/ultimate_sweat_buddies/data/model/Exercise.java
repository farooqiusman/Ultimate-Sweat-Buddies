package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

public abstract class Exercise {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("user_email")
    private String userEmail;

    public Exercise(Integer id, String name, String userEmail) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
