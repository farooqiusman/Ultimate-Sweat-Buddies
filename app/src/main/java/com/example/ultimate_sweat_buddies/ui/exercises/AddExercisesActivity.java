package com.example.ultimate_sweat_buddies.ui.exercises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;

public class AddExercisesActivity extends AppCompatActivity {

    EnduranceFragment end = new EnduranceFragment();
    WeightFragment weight = new WeightFragment();

    TextView tv;


    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);
        s = findViewById(R.id.mySwitch);
        FragmentManager fm = getSupportFragmentManager();


        fm.beginTransaction()
                .add(R.id.fragment_container, weight)
                .commit();
        s.setText("Weight");

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