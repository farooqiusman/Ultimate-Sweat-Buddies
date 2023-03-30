package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWeightExercise;

public class WeightFragment extends Fragment {


    EditText exerciseName, sets, reps, weight;
    Button submit;
    ExercisesViewModel eVm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);


        exerciseName = view.findViewById(R.id.etName);
        sets = view.findViewById(R.id.etSets);
        reps = view.findViewById(R.id.etReps);
        weight = view.findViewById(R.id.etWeight);
        submit = view.findViewById(R.id.btn_submit);
        Intent intent = new Intent(getActivity(), ExercisesFragment.class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostWeightExercise pw;
                String getName = exerciseName.getText().toString();
                int getSets = Integer.parseInt(sets.getText().toString());
                int getReps = Integer.parseInt(reps.getText().toString());
                int getWeight = Integer.parseInt(weight.getText().toString());

                pw = new PostWeightExercise("weight", getName, "akshat@akshat.com", getSets, getReps, getWeight);
                eVm.postWeightExercises(pw);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

//                fragmentManager.popBackStack();
            }
        });


        return view;
    }
}