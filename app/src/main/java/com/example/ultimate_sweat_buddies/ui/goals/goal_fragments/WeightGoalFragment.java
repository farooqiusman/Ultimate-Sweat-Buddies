package com.example.ultimate_sweat_buddies.ui.goals.goal_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightGoalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightGoalFragment extends Fragment {

    public static WeightGoalFragment instance;

    public WeightGoalFragment() {
        // Required empty public constructor
    }

    public static WeightGoalFragment newInstance(){
        return new WeightGoalFragment();
    }
    public static WeightGoalFragment getInstance(){
        if (instance == null){
            instance = newInstance();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight_goal, container, false);
    }
}