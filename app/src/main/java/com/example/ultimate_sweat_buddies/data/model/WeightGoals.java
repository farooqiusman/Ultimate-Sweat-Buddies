package com.example.ultimate_sweat_buddies.data.model;

import com.google.gson.annotations.SerializedName;

public class WeightGoals extends Goal{
    @SerializedName("exercise_id")
    private Integer exerciseId;

    @SerializedName("serts")
    private Integer sets;

    @SerializedName("reps")
    private Integer reps;

    @SerializedName("weight")
    private Integer weight;

    public WeightGoals(Integer id, String userEmail,
                       String deadLine, Integer completed,
                       String creationDate, Integer exerciseId,
                       Integer sets, Integer reps, Integer weight) {
        super(id, userEmail, deadLine, completed, creationDate);
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
