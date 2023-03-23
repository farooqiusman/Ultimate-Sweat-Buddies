package com.example.ultimate_sweat_buddies.ui.workout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;

public class WorkoutFragment extends Fragment {

    private WorkoutViewModel mViewModel;

    public static WorkoutFragment instance;
    public static WorkoutFragment newInstance() {
        return new WorkoutFragment();
    }
    public static WorkoutFragment getInstance() {
        if (instance == null) instance = newInstance();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        // TODO: Use the ViewModel
    }

}