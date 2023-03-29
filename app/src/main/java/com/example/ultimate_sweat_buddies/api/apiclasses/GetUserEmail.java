package com.example.ultimate_sweat_buddies.api.apiclasses;

public class GetUserEmail {
    private String Status;

    private String userEmail;
    private float Response;

    public GetUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

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
