package com.example.ultimate_sweat_buddies.data.model;

public class LoginUser {

    private String userName;
    private String userEmail;

    public LoginUser(){
        this.userName = null;
        this.userEmail = null;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
