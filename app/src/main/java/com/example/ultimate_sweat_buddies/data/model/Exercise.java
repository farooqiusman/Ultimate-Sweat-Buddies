package com.example.ultimate_sweat_buddies.data.model;

public abstract class Exercise {

    private int id;
    private String name;
    private String userEmail;

    public Exercise(int id, String name, String userEmail) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
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
}
