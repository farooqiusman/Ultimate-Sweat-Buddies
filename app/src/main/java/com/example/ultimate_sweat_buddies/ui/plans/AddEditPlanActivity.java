package com.example.ultimate_sweat_buddies.ui.plans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.databinding.ActivityAddEditPlanBinding;
import com.example.ultimate_sweat_buddies.ui.exercises.ExercisesAdapter;
import com.example.ultimate_sweat_buddies.ui.exercises.ExercisesViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddEditPlanActivity extends AppCompatActivity implements ExercisesAdapter.ExercisesAdapterListener {

    private final ExercisesViewModel exerciseViewModel = new ExercisesViewModel();
    private final PlansViewModel plansViewModel = new PlansViewModel();

    private ExercisesAdapter addedExercisesAdapter;
    private ExercisesAdapter unaddedExercisesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddEditPlanBinding binding = ActivityAddEditPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Check whether we are adding or editing a plan
        boolean editingPlan = getIntent().getStringExtra("update_type").equals("edit");

        // Get all of the exercises, then filter out the ones that are in this plan if we are editing a plan
        List<Exercise> unaddedExercises = new ArrayList<>();
        try {
            unaddedExercises = exerciseViewModel.getExercises("test@test.com").get();  // Waits for the future to return its result
        } catch (ExecutionException | InterruptedException e) {
            Log.d("error_getting_exercises", "could not get exercises");
            e.printStackTrace();
        }

        List<Exercise> addedExercises = new ArrayList<>();
        if (editingPlan) {
            try {
                addedExercises = plansViewModel.getWorkoutPlanExercises("test@test.com", "Push").get();   // Waits for the future to return its result

                // Remove the added exercises from unaddedExercises, since it has all of the exercises initially
                if (addedExercises.size() > 0) {
                    for (int i = unaddedExercises.size() - 1; i >= 0; i--) {
                        for (Exercise e : addedExercises) {
                            if (unaddedExercises.get(i).getId() == e.getId()) {
                                unaddedExercises.remove(i);
                            }
                        }
                    }
                }
            } catch (ExecutionException | InterruptedException e) {
                Log.d("error_getting_workout_plan_exercises", "could not get exercises");
                e.printStackTrace();
            }
        }

        RecyclerView rvAddedExercises = binding.rvAddedExercises;
        RecyclerView rvUnaddedExercises = binding.rvUnaddedExercises;

        addedExercisesAdapter = new ExercisesAdapter(addedExercises, this, ExercisesAdapter.ExerciseListType.REMOVE);
        unaddedExercisesAdapter = new ExercisesAdapter(unaddedExercises, this, ExercisesAdapter.ExerciseListType.ADD);

        addedExercisesAdapter.setListener(this);
        unaddedExercisesAdapter.setListener(this);

        rvAddedExercises.setAdapter(addedExercisesAdapter);
        rvAddedExercises.setLayoutManager(new LinearLayoutManager(this));

        rvUnaddedExercises.setAdapter(unaddedExercisesAdapter);
        rvUnaddedExercises.setLayoutManager(new LinearLayoutManager(this));

        // Setup save plan button
        binding.fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(binding.etTitle.getText());
                String daysOfWeek = buildDaysOfWeekString(binding);
                try {
                    plansViewModel.postPlan("test@test.com", title, daysOfWeek, addedExercisesAdapter.getExercises()).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                Intent mainIntent = new Intent(AddEditPlanActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private String buildDaysOfWeekString(ActivityAddEditPlanBinding binding) {
        String daysOfWeek = "";
        if (binding.cbSunday.isChecked()) daysOfWeek += "Su";
        if (binding.cbMonday.isChecked()) daysOfWeek += "M";
        if (binding.cbTuesday.isChecked()) daysOfWeek += "Tu";
        if (binding.cbWednesday.isChecked()) daysOfWeek += "W";
        if (binding.cbThursday.isChecked()) daysOfWeek += "Th";
        if (binding.cbFriday.isChecked()) daysOfWeek += "F";
        if (binding.cbSaturday.isChecked()) daysOfWeek += "Sa";
        return daysOfWeek;
    }

    @Override
    public void onExerciseRemoved(Exercise ex, ExercisesAdapter.ExerciseListType type) {
        switch (type) {
            case ADD:
                addedExercisesAdapter.addExercise(ex);
                break;
            case REMOVE:
                unaddedExercisesAdapter.addExercise(ex);
                break;
            default:
                Log.e("invalid_plan_exercises_adapter_type", "Invalid type: " + type.name());
        }
    }
}