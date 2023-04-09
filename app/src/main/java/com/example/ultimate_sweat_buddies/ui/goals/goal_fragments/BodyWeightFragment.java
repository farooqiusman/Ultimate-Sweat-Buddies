package com.example.ultimate_sweat_buddies.ui.goals.goal_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BodyWeightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BodyWeightFragment extends Fragment {

    public static BodyWeightFragment instance;

    public BodyWeightFragment() {
        // Required empty public constructor
    }

    public static BodyWeightFragment newInstance(){
        return new BodyWeightFragment();
    }
    public static BodyWeightFragment getInstance(){
        if (instance == null){
            instance = newInstance();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_body_weight, container, false);
    }
}