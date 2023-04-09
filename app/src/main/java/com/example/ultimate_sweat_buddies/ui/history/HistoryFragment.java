package com.example.ultimate_sweat_buddies.ui.history;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.plans.PlansAdapter;
import com.example.ultimate_sweat_buddies.ui.workout.WorkoutViewModel;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel = new HistoryViewModel();
    private static HistoryFragment instance;

    private ImageButton ibPrev;
    private TextView tvWorkoutDate;
    private ImageButton ibNext;

    public static HistoryFragment getInstance() {
        if (instance == null) instance = new HistoryFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ibPrev = view.findViewById(R.id.ibPrev);
        tvWorkoutDate = view.findViewById(R.id.tvWorkoutDate);
        ibNext = view.findViewById(R.id.ibNext);

        // Get the user's workout logs from the app's files directory
        Context ctx = getContext();
        if (ctx != null) {
            File filesDir = ctx.getFilesDir();
            List<String> workoutLogs = historyViewModel.readLoggedWorkouts(filesDir);
        }

        HistoryAdapter adapter = new PlansAdapter(plans, getContext(), plansViewModel, PlansAdapter.PlanListType.SELECT);

        adapter.setListener(this);  // Make this fragment listen for selecting a plan

        rvSelectPlan.setAdapter(adapter);
        rvSelectPlan.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}