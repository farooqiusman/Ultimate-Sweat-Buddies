package com.example.ultimate_sweat_buddies.ui.exercises;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercises;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    List<EnduranceExercise> enduranceExercises;
    List<WeightExercise> weightExercises;





    public List<Exercises> getData() {
        List<Exercises> data = new ArrayList<>();


        return data;
    }


}