package com.example.ultimate_sweat_buddies.ui.exercises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;

public class AddEditExercisesActivity extends AppCompatActivity {

    EnduranceFragment end = new EnduranceFragment();
    WeightFragment weight = new WeightFragment();

    TextView tv;
    Switch s;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_exercises);
        s = findViewById(R.id.mySwitch);
        FragmentManager fm = getSupportFragmentManager();


        fm.beginTransaction()
                .add(R.id.fragment_container, weight)
                .commit();
        s.setText("Weight");
        Intent intent = getIntent();
        String type = "";
        int id = intent.getIntExtra("id", 0);
        String exerciseType;
        String name;
        type = intent.getStringExtra("update_type");
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("update_type", type);
        if(type!=null && type.equals("edit")){
            s.setVisibility(View.GONE);
            exerciseType = intent.getStringExtra("exercise_type");
            name = intent.getStringExtra("name");
            bundle.putString("name", name);
            if(exerciseType.equals("weight")){
                int sets = intent.getIntExtra("sets", 0);
                bundle.putInt("sets", sets);
                int reps = intent.getIntExtra("reps", 0);
                bundle.putInt("reps", reps);
                int w = intent.getIntExtra("weight", 0);
                bundle.putInt("weight", w);
                weight.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container, weight).commit();
            }else if(exerciseType.equals("endurance")){

                int hours = intent.getIntExtra("hours", 0);
                bundle.putInt("hours", hours);
                int minutes = intent.getIntExtra("minutes", 0);
                bundle.putInt("minutes", minutes);
                int seconds = intent.getIntExtra("seconds", 0);
                bundle.putInt("seconds", seconds);
                end.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container, end).commit();
            }
        }



        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Fragment fragment;
                if (isChecked) {
                    fragment = end;
                    s.setText("Endurance");
                } else {
                    fragment = weight;
                    s.setText("Weight");
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });


    }


}