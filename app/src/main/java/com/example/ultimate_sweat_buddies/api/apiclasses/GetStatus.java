package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.google.gson.annotations.SerializedName;

public class GetStatus {
    @SerializedName("Status")
    private String status;
    @SerializedName("Response")
    private String response;


    // Getter Methods

    public String getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    // Setter Methods

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

