package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWeightExercise;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;

import java.util.concurrent.ExecutionException;

public class WeightFragment extends Fragment {

    private EditText exerciseName, sets, reps, weight;
    private Button submit;
    private ExercisesViewModel eVm;



    private String email = StoreLoginUser.user.getUserEmail();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        eVm = new ViewModelProvider(this).get(ExercisesViewModel.class);
        exerciseName = view.findViewById(R.id.etName);
        sets = view.findViewById(R.id.etSets);
        reps = view.findViewById(R.id.etReps);
        weight = view.findViewById(R.id.etWeight);
        submit = view.findViewById(R.id.btn_submit);
//        eVm = new ViewModelProvider(this).get(ExercisesViewModel.class);
        if (eVm == null) {
            Log.e("WeightFragment", "eVm is null");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostWeightExercise pw;
                String getName = exerciseName.getText().toString();
                int getSets = Integer.parseInt(sets.getText().toString());
                int getReps = Integer.parseInt(reps.getText().toString());
                int getWeight = Integer.parseInt(weight.getText().toString());

                //error checking
                // error checking



                pw = new PostWeightExercise("weight", getName, email, getSets, getReps, getWeight);
                try {
                    eVm.postWeightExercises(pw).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().finish();
            }
        });

        return view;
    }
}