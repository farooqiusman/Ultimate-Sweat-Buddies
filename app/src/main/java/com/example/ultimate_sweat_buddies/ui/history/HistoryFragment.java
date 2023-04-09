package com.example.ultimate_sweat_buddies.ui.history;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.plans.PlansAdapter;
import com.example.ultimate_sweat_buddies.ui.workout.WorkoutViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel = new HistoryViewModel();
    private static HistoryFragment instance;

    private ConstraintLayout clHistoryHeader;
    private ImageButton ibPrev;
    private TextView tvWorkoutDate;
    private TextView tvPlanName;
    private ImageButton ibNext;
    private TextView tvNoHistory;
    private RecyclerView rvWorkoutLog;

    private List<String> workoutLogs;

    public static HistoryFragment getInstance() {
        if (instance == null) instance = new HistoryFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        clHistoryHeader = view.findViewById(R.id.clHistoryHeader);
        ibPrev = view.findViewById(R.id.ibPrev);
        tvWorkoutDate = view.findViewById(R.id.tvWorkoutDate);
        tvPlanName = view.findViewById(R.id.tvPlanName);
        ibNext = view.findViewById(R.id.ibNext);
        tvNoHistory = view.findViewById(R.id.tvNoHistory);
        rvWorkoutLog = view.findViewById(R.id.rvWorkoutLog);

        // Get the user's workout logs from the app's files directory
        Context ctx = getContext();
        if (ctx == null) {
            Log.d("history_fragment", "could not get context");
            return view;
        }

        File filesDir = ctx.getFilesDir();
//        historyViewModel.deleteAllWorkoutLogs(filesDir);
        workoutLogs = historyViewModel.readLoggedWorkouts(filesDir);

        if (workoutLogs.isEmpty()) {
            tvNoHistory.setVisibility(View.VISIBLE);
            clHistoryHeader.setVisibility(View.GONE);
            return view;
        }

        // Get the exercise logs for the most recent workout log and put them into the adapter
        List<String> workoutLogLines = Arrays.asList(workoutLogs.get(0).split("\n"));

        // Get the workout date and plan name from the header and set the text views accordingly
        String[] headerLineTokens = workoutLogLines.get(0).split(",");
        tvWorkoutDate.setText(headerLineTokens[0]);
        tvPlanName.setText(headerLineTokens[1]);

        // Add all the other (exercise) lines to the adapter
        HistoryAdapter adapter = new HistoryAdapter(workoutLogLines.subList(1, workoutLogLines.size()));

        rvWorkoutLog.setAdapter(adapter);
        rvWorkoutLog.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}