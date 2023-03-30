package com.example.ultimate_sweat_buddies.ui.goals;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ultimate_sweat_buddies.R;

public class GoalsFragment extends Fragment {

    private GoalsViewModel mViewModel;

    public static GoalsFragment instance;
    public static GoalsFragment newInstance() {
        return new GoalsFragment();
    }
    public static GoalsFragment getInstance() {
        if (instance == null) instance = newInstance();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        Button addGoal = view.findViewById(R.id.addGoal);
        Intent intent = new Intent(getActivity(), AddGoalActivity.class);
        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        // TODO: Use the ViewModel
    }

}