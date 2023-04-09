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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    private LinearLayout llSwitchOrFinish;
    private Button btnSwitchPlan;
    private Button btnFinishWorkout;
    private LinearLayout llEditWeightExercise;
    private EditText etEditSets;
    private EditText etEditReps;
    private EditText etEditWeight;
    private LinearLayout llEditEnduranceExercise;
    private EditText etEditHours;
    private EditText etEditMinutes;
    private EditText etEditSeconds;

    private StringBuilder logOutput;
    private Date workoutDate;

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
        llSwitchOrFinish = view.findViewById(R.id.llSwitchOrFinish);
        btnSwitchPlan = view.findViewById(R.id.btnSwitchPlan);
        btnFinishWorkout = view.findViewById(R.id.btnFinishWorkout);
        llEditWeightExercise = view.findViewById(R.id.llEditWeightExercise);
        etEditSets = view.findViewById(R.id.etEditSets);
        etEditReps = view.findViewById(R.id.etEditReps);
        etEditWeight = view.findViewById(R.id.etEditWeight);
        llEditEnduranceExercise = view.findViewById(R.id.llEditEnduranceExercise);
        etEditHours = view.findViewById(R.id.etEditHours);
        etEditMinutes = view.findViewById(R.id.etEditMinutes);
        etEditSeconds = view.findViewById(R.id.etEditSeconds);

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
            llSwitchOrFinish.setVisibility(View.GONE);
            llEditWeightExercise.setVisibility(View.GONE);
            llEditEnduranceExercise.setVisibility(View.GONE);
        });

        btnFinishWorkout.setOnClickListener(v -> {
            try {
                Context ctx = getContext();
                if (ctx != null) {
                    File filesDir = ctx.getFilesDir();
                    workoutViewModel.logWorkoutToFile(filesDir, workoutDate, logOutput.toString());
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
            workoutDate = new Date();
            String dateText = workoutViewModel.getFormattedDateString(workoutDate, "yyyy-MM-dd");

            // Create the output string builder with the workout date and chosen plan name (header of the log file)
            logOutput = new StringBuilder(String.format("%s,%s\n", dateText, plan.getTitle()));

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
                llSwitchOrFinish.setVisibility(View.VISIBLE);
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onExerciseCompleted(Exercise ex) {
        // Set all the edit fields to blank if the completed exercise is the one we are editing
        if (ex.getId() == workoutViewModel.getExerciseBeingEditedId()) {
            etEditSets.setText("");
            etEditReps.setText("");
            etEditWeight.setText("");
            etEditHours.setText("");
            etEditMinutes.setText("");
            etEditSeconds.setText("");
            workoutViewModel.setExerciseBeingEditedId(-1);
            llEditWeightExercise.setVisibility(View.GONE);
            llEditEnduranceExercise.setVisibility(View.GONE);
        }

        // Add exercise details to log
        if (ex instanceof WeightExercise) {
            WeightExercise we = (WeightExercise) ex;
            logOutput.append(String.format("weight,%s,%d,%d,%d\n", we.getName(), we.getSets(), we.getReps(), we.getWeight()));
        } else if (ex instanceof EnduranceExercise) {
            EnduranceExercise ee = (EnduranceExercise) ex;
            logOutput.append(String.format("endurance,%s,%s\n", ee.getName(), ee.getTime()));
        }
    }

    @Override
    public void onEditClicked(Exercise ex) {
        // Mark this exercise id as being edited so we can know which adapter item needs to receive
        // the edited values
        workoutViewModel.setExerciseBeingEditedId(ex.getId());

        if (ex instanceof WeightExercise) {

            // Hide the Endurance Exercise edit fields if they are showing
            if (llEditEnduranceExercise.getVisibility() == View.VISIBLE) {
                etEditHours.setText("");
                etEditMinutes.setText("");
                etEditSeconds.setText("");
                llEditEnduranceExercise.setVisibility(View.GONE);
            }

            llEditWeightExercise.setVisibility(View.VISIBLE);
            WeightExercise we = (WeightExercise) ex;
            etEditSets.setText(String.valueOf(we.getSets()));
            etEditReps.setText(String.valueOf(we.getReps()));
            etEditWeight.setText(String.valueOf(we.getWeight()));
        } else if (ex instanceof EnduranceExercise) {

            // Hide the Weight Exercise edit fields if they are showing
            if (llEditWeightExercise.getVisibility() == View.VISIBLE) {
                etEditSets.setText("");
                etEditReps.setText("");
                etEditWeight.setText("");
                llEditWeightExercise.setVisibility(View.GONE);
            }

            llEditEnduranceExercise.setVisibility(View.VISIBLE);
            EnduranceExercise ee = (EnduranceExercise) ex;

            // Parse the hh:MM:ss string and populate the EditTexts
            String time = ee.getTime();
            int firstColonIdx = time.indexOf(':');
            int secondColonIdx = (firstColonIdx + 1) + time.substring(firstColonIdx + 1).indexOf(':');
            etEditHours.setText(time.substring(0, firstColonIdx));
            etEditMinutes.setText(time.substring(firstColonIdx + 1, secondColonIdx));
            etEditSeconds.setText(time.substring(secondColonIdx + 1));
        }
    }

    @Override
    public Exercise getValidEdits(Exercise ex) {

        // If the exercise being completed (i.e., the one with id exerciseId) is not the exercise
        // being edited, then we do not have valid edits (for this exercise)
        if (ex.getId() != workoutViewModel.getExerciseBeingEditedId()) {
            return null;
        }

        if (llEditWeightExercise.getVisibility() == View.VISIBLE) {

            String setsText = etEditSets.getText().toString();
            String repsText = etEditReps.getText().toString();
            String weightText = etEditWeight.getText().toString();
            int sets, reps, weight;
            try {
                sets = Integer.parseInt(setsText);
                reps = Integer.parseInt(repsText);
                weight = Integer.parseInt(weightText);
            } catch (NumberFormatException e) {
                return null;
            }

            if (etEditSets.getText().length() != 0
                    && etEditReps.getText().length() != 0
                    && etEditWeight.getText().length() != 0
            ) {
                return new WeightExercise(ex.getId(), ex.getName(), ex.getUserEmail(), sets, reps, weight);
            }
            Toast.makeText(getContext(), "Please enter sets, reps, and weight", Toast.LENGTH_SHORT).show();
            return null;
        }

        if (llEditEnduranceExercise.getVisibility() == View.VISIBLE) {

            String hoursText = etEditHours.getText().toString();
            String minutesText = etEditMinutes.getText().toString();
            String secondsText = etEditSeconds.getText().toString();
            int hours, minutes, seconds;
            try {
                hours = Integer.parseInt(hoursText);
                minutes = Integer.parseInt(minutesText);
                seconds = Integer.parseInt(secondsText);
            } catch (NumberFormatException e) {
                return null;
            }

            if ((hoursText.length() >= 1 && hoursText.length() <= 2 && hours >= 0 && hours < 24)
                    && (minutesText.length() >= 1 && minutesText.length() <= 2 && minutes >= 0 && minutes < 60)
                    && (secondsText.length() >= 1 && secondsText.length() <= 2 && seconds >= 0 && seconds < 60)
            ) {
                String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                return new EnduranceExercise(ex.getId(), ex.getName(), ex.getUserEmail(), time);
            } else {
                Toast.makeText(getContext(), "Please enter hours, minutes, and seconds", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return null;
    }
}