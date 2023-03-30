package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

public class BodyWeightGoals extends Goal{

    @SerializedName("description")
    private String descrtiption;

    public BodyWeightGoals(Integer id, String userEmail,
                           String deadLine, Integer completed,
                           String creationDate, String descrtiption) {
        super(id, userEmail, deadLine, completed, creationDate);
        this.descrtiption = descrtiption;
    }

    public String getDescrtiption() {
        return descrtiption;
    }

    public void setDescrtiption(String descrtiption) {
        this.descrtiption = descrtiption;
    }
}
