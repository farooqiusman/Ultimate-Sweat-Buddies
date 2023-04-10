package com.example.ultimate_sweat_buddies.ui.plans;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.example.ultimate_sweat_buddies.R;

import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class PlansFragment extends Fragment implements PlansAdapter.PlansAdapterEditListener {

    private final PlansViewModel mViewModel = new PlansViewModel();
    private static PlansFragment instance;

    private PlansAdapter adapter;
    private RecyclerView recyclerView;

    public static PlansFragment getInstance() {
        if (instance == null) instance = new PlansFragment();
        return instance;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plans, container, false);
        recyclerView = view.findViewById(R.id.Recycler_view);

        setupPlansAdapter();

        FloatingActionButton fab = view.findViewById(R.id.add_btn);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddEditPlanActivity.class);
            intent.putExtra("update_type", "add");
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupPlansAdapter();    // Refresh the plans list
    }

    private void setupPlansAdapter() {
        List<WorkoutPlan> data = null;
        try {
            data = mViewModel.getPlans(StoreLoginUser.user.getUserEmail()).get();  // Waits for the future to return its result
        } catch (ExecutionException | InterruptedException e) {
            Log.d("error_getting_plans", "could not get plans");
            e.printStackTrace();
        }

        adapter = new PlansAdapter(data, getContext(), mViewModel, PlansAdapter.PlanListType.EDIT_DELETE);
        adapter.setEditPlanListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onEditPlanClicked(WorkoutPlan plan) {
        Intent editPlanIntent = new Intent(getContext(), AddEditPlanActivity.class);
        editPlanIntent.putExtra("update_type", "edit");
        editPlanIntent.putExtra("title", plan.getTitle());
        editPlanIntent.putExtra("days_of_week", plan.getDaysOfWeek());
        editPlanIntent.putExtra("creation_date", plan.getCreationDate());

        // Get the exercise IDs for this workout plan as an array and pass it as well
        try {
            List<Exercise> planExercises = mViewModel.getWorkoutPlanExercises(
                    StoreLoginUser.user.getUserEmail(), plan.getTitle()).get();
            int[] exerciseIds = planExercises.stream().mapToInt(Exercise::getId).toArray();
            editPlanIntent.putExtra("exercise_ids", exerciseIds);
            startActivity(editPlanIntent);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}