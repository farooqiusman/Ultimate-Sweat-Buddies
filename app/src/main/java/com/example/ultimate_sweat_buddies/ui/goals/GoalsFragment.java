package com.example.ultimate_sweat_buddies.ui.goals;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Button;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.Goal;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GoalsFragment extends Fragment {


    private GoalsViewModel goalsViewModel = new GoalsViewModel();
    private static GoalsFragment instance;

    public static GoalsFragment getInstance() {
        if (instance == null) instance = new GoalsFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.goal_recycle_viewer);
        Button addGoal = view.findViewById(R.id.addButton);
        Intent intent = new Intent(getActivity(), AddGoalActivity.class);

        List<Goal> data = null;
        try{
            data = goalsViewModel.getGoals("test@test.com").get();
        }catch (ExecutionException | InterruptedException e){
            Log.d("error_getting_goals", "could not get goals");
            e.printStackTrace();
        }

        GoalsAdapter adapter = new GoalsAdapter(data, getContext(), goalsViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}