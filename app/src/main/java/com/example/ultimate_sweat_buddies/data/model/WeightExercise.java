package com.example.ultimate_sweat_buddies.data.model;

public class WeightExercise extends Exercise {

    private int sets;
    private int reps;
    private int weight;

    public WeightExercise(int id, String name, String userEmail, int sets, int reps, int weight) {
        super(id, name, userEmail);
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
