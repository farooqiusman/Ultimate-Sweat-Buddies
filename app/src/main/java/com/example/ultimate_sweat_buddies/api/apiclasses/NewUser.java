package com.example.ultimate_sweat_buddies.api.apiclasses;

public class NewUser {
    private String email, username, password;

    public NewUser(String userEmail, String userPassword, String userName){
        this.email = userEmail;
        this.password = userPassword;
        this.username = userName;
    }

    public String getUserEmail() {
        return email;
    }

    public String getUserName() {
        return username;
    }

    public String getUserPassword() {
        return password;
    }
}
