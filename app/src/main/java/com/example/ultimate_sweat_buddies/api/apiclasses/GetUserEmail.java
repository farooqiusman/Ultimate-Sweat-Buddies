package com.example.ultimate_sweat_buddies.api.apiclasses;

public class GetUserEmail {
    private String Status;

    private float Response;

    // Getter Methods

    public String getStatus() {
        return Status;
    }

    public float getResponse() {
        return Response;
    }

    // Setter Methods

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setResponse(float Response) {
        this.Response = Response;
    }
}
