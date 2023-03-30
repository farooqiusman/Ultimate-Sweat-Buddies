package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostWeightExercise;

import java.util.concurrent.ExecutionException;

public class WeightFragment extends Fragment {

    private EditText exerciseName, sets, reps, weight;
    private Button submit;
    private ExercisesViewModel eVm;

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
        Intent intent = new Intent(getContext(), MainActivity.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostWeightExercise pw;
                String getName = exerciseName.getText().toString();
                int getSets = Integer.parseInt(sets.getText().toString());
                int getReps = Integer.parseInt(reps.getText().toString());
                int getWeight = Integer.parseInt(weight.getText().toString());

                //error checking
                if(getSets == 0 || getReps == 0 || getWeight == 0){
                    Toast.makeText(getContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    sets.setText("");
                    reps.setText("");
                    weight.setText("");
                    return;
                }

                if(getName.equals("")){
                    Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    sets.setText("");
                    reps.setText("");
                    weight.setText("");
                    return;
                }


                pw = new PostWeightExercise("weight", getName, "akshat@akshat.com", getSets, getReps, getWeight);
                try {
                    eVm.postWeightExercises(pw).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });

        return view;
    }
}