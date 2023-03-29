package com.example.ultimate_sweat_buddies.data.model;

public class WeightExercise {

    private int id;
    private String exerciseName;
    private int sets;
    private int reps;
    private int weight;
    private String userEmail;


    public WeightExercise(int id, String exerciseName, int sets, int reps, int weight, String userEmail) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.userEmail = userEmail;
    }


    public int getId() {
        return id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public String getUserEmail() {
        return userEmail;
    }


}
