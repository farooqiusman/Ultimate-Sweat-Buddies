package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

public class MiscGoals extends Goal{
    @SerializedName("description")
    private String description;
    public MiscGoals(Integer id, String userEmail,
                     String deadLine, Integer completed,
                     String creationDate, String description) {
        super(id, userEmail, deadLine, completed, creationDate);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
