package com.example.ultimate_sweat_buddies.ui.workout;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.plans.PlansAdapter;
import com.example.ultimate_sweat_buddies.ui.plans.PlansViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WorkoutFragment extends Fragment implements PlansAdapter.PlansAdapterListener, WorkoutAdapter.WorkoutAdapterListener {

    private WorkoutViewModel workoutViewModel = new WorkoutViewModel();
    private PlansViewModel plansViewModel = new PlansViewModel();
    private static WorkoutFragment instance;
    private TextView tvTitle;
    private RecyclerView rvSelectPlan;
    private RecyclerView rvWorkout;
    private Button btnSwitchPlan;
    private Button btnFinishWorkout;

    private StringBuilder logOutput;

    public WorkoutFragment() {
    }

    public static WorkoutFragment getInstance() {
        if (instance == null) instance = new WorkoutFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        tvTitle = view.findViewById(R.id.tvTitle);
        rvSelectPlan = view.findViewById(R.id.rvSelectPlan);
        rvWorkout = view.findViewById(R.id.rvWorkout);
        btnSwitchPlan = view.findViewById(R.id.btnSwitchPlan);
        btnFinishWorkout = view.findViewById(R.id.btnFinishWorkout);

        // Get the users plans
        List<WorkoutPlan> plans = null;
        try {
            plans = plansViewModel.getPlans(StoreLoginUser.user.getUserEmail()).get();  // Waits for the future to return its result
            String currentDayOfWeek = workoutViewModel.getDayOfWeek(new Date());
            for (int i = plans.size() - 1; i >= 0; i--) {
                if (!plans.get(i).getDaysOfWeek().contains(currentDayOfWeek)) {
                    plans.remove(i);
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            Log.d("error_getting_plans", "could not get plans");
            e.printStackTrace();
        }

        PlansAdapter adapter = new PlansAdapter(plans, getContext(), plansViewModel, PlansAdapter.PlanListType.SELECT);

        adapter.setListener(this);  // Make this fragment listen for selecting a plan

        rvSelectPlan.setAdapter(adapter);
        rvSelectPlan.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnSwitchPlan.setOnClickListener(v -> {
            // Show the plans selector
            tvTitle.setVisibility(View.VISIBLE);
            rvSelectPlan.setVisibility(View.VISIBLE);

            // Hide the workout part
            rvWorkout.setVisibility(View.GONE);
            btnSwitchPlan.setVisibility(View.GONE);
            btnFinishWorkout.setVisibility(View.GONE);
        });

        btnFinishWorkout.setOnClickListener(v -> {
            String filename = workoutViewModel.getFormattedFilename(new Date());
            try {
                Context ctx = getContext();
                if (ctx != null) {
                    File filesDir = ctx.getFilesDir();
                    workoutViewModel.logWorkoutToFile(filesDir, filename, logOutput.toString());
                }
            } catch (NullPointerException ex) {
                Log.e("finish_workout", ex.getMessage());
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPlanSelected(WorkoutPlan plan) {
        try {
            // Create the output string builder
            logOutput = new StringBuilder(String.format("Plan Name: %s\n", plan.getTitle()));

            List<Exercise> workoutPlanExercises = plansViewModel.getWorkoutPlanExercises(plan.getUserEmail(), plan.getTitle()).get();

            if (workoutPlanExercises.isEmpty()) {
                Snackbar.make(this.getView(), "Add some exercises to this plan to use it.", Snackbar.LENGTH_SHORT).show();
            } else {
                // Hide the plans selector
                tvTitle.setVisibility(View.GONE);
                rvSelectPlan.setVisibility(View.GONE);

                // Setup and show the workout part
                WorkoutAdapter adapter = new WorkoutAdapter(workoutPlanExercises, getContext());
                adapter.setListener(this);  // Listen for completed exercises so we can get their details
                rvWorkout.setAdapter(adapter);
                rvWorkout.setLayoutManager(new LinearLayoutManager(getActivity()));

                rvWorkout.setVisibility(View.VISIBLE);
                btnSwitchPlan.setVisibility(View.VISIBLE);
                btnFinishWorkout.setVisibility(View.VISIBLE);
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExerciseCompleted(Exercise ex) {
        // Add exercise details to log
        if (ex instanceof WeightExercise) {
            WeightExercise we = (WeightExercise) ex;
            logOutput.append(String.format("Exercise: %s, Sets: %d, Reps: %d, Weight: %d\n", we.getName(), we.getSets(), we.getReps(), we.getWeight()));
        } else if (ex instanceof EnduranceExercise) {
            EnduranceExercise ee = (EnduranceExercise) ex;
            logOutput.append(String.format("Exercise: %s, Time: %s\n", ee.getName(), ee.getTime()));
        }
    }
}