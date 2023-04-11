package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.PostEnduranceExercise;
import com.example.ultimate_sweat_buddies.api.apiclasses.PutEnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;

import java.util.concurrent.ExecutionException;

public class EnduranceFragment extends Fragment {

    private EditText exerciseName, hours, mins, secs;
    private ExercisesViewModel eVm;
    private Button btn;

    private String email = StoreLoginUser.user.getUserEmail();
    private String type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_endurance, container, false);
        eVm = new ViewModelProvider(this).get(ExercisesViewModel.class);
        exerciseName = view.findViewById(R.id.etName);
        hours = view.findViewById(R.id.etHours);
        mins = view.findViewById(R.id.etMins);
        secs = view.findViewById(R.id.etSecs);
        btn = view.findViewById(R.id.btn_submit);
        Intent intent = new Intent(getContext(), MainActivity.class);
        Bundle bundle = getArguments();




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostEnduranceExercise pe;

                int id = bundle.getInt("id");


                String getName = exerciseName.getText().toString();
                int getHours = Integer.parseInt(hours.getText().toString());
                int getMins = Integer.parseInt(mins.getText().toString());
                int getSecs = Integer.parseInt(secs.getText().toString());
                Log.e("akshat", "onClick: " + getName + getHours + getMins + getSecs);
                //check if atleast one of the fields is not 0
                if(getHours == 0 && getMins == 0 && getSecs == 0){
                    Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    hours.setText("0");
                    mins.setText("0");
                    secs.setText("0");
                    return;
                }
                if (getName.equals("")){
                    Toast.makeText(getContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    hours.setText("0");
                    mins.setText("0");
                    secs.setText("0");
                    return;
                }

                //logic to not get more 10 hours
                if(getHours > 10){
                    Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    hours.setText("0");
                    mins.setText("0");
                    secs.setText("0");
                    return;
                }
                //logic to not get more than 60 mins
                if(getMins > 60){
                    Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    hours.setText("0");
                    mins.setText("0");
                    secs.setText("0");
                    return;
                }

                //logic to not get more than 60 secs
                if(getSecs > 60) {
                    Toast.makeText(getContext(), "Please enter a valid time", Toast.LENGTH_SHORT).show();
                    exerciseName.setText("");
                    hours.setText("0");
                    mins.setText("0");
                    secs.setText("0");
                    return;
                }

                if(bundle != null){
                    //set the values to the fields
                    exerciseName.setText(bundle.getString("name"));
                    hours.setText(String.valueOf(bundle.getInt("hours")));
                    mins.setText(String.valueOf(bundle.getInt("minutes")));
                    secs.setText(String.valueOf(bundle.getInt("seconds")));
                    type = getArguments().getString("update_type");
                }



                String time = "";
                time = time + getHours + ":" + getMins + ":" + getSecs;


                if(type.equals("edit")){
                    PutEnduranceExercise pee = new PutEnduranceExercise(id, getName, email, time);
                    try {
                        eVm.putEnduranceExercise(pee).get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    getActivity().finish();
                    return;
                }else {
                    pe = new PostEnduranceExercise("endurance", getName, email, time);
                    try {
                        eVm.postEnduranceExercises(pe).get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                getActivity().finish();
            }
        });



        return view;
    }
}