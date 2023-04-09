package com.example.ultimate_sweat_buddies.ui.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.databinding.ActivityAddGoalBinding;
import com.example.ultimate_sweat_buddies.ui.exercises.ExercisesFragment;
import com.example.ultimate_sweat_buddies.ui.goals.goal_fragments.BodyWeightFragment;
import com.example.ultimate_sweat_buddies.ui.goals.goal_fragments.EnduranceGoalFragment;
import com.example.ultimate_sweat_buddies.ui.goals.goal_fragments.MiscGoalFragment;
import com.example.ultimate_sweat_buddies.ui.goals.goal_fragments.WeightGoalFragment;
import com.example.ultimate_sweat_buddies.ui.history.HistoryFragment;
import com.example.ultimate_sweat_buddies.ui.plans.PlansFragment;
import com.example.ultimate_sweat_buddies.ui.profile.ProfileFragment;
import com.example.ultimate_sweat_buddies.ui.workout.WorkoutFragment;
import com.google.android.material.navigation.NavigationBarView;

public class AddGoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddGoalBinding binding = ActivityAddGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final NavigationBarView bnvGoalview = binding.goalnav;
        bnvGoalview.setOnItemSelectedListener(item -> switchFragment(item.getItemId()));
        switchFragment(R.id.navMisc);
    }

    @SuppressLint("NonConstantResourceId")
    private boolean switchFragment(int fragmentId) {
        // Get the fragment for the clicked on navigation item
        Fragment fragment;
        switch (fragmentId) {
            case R.id.navMisc:
                fragment = MiscGoalFragment.getInstance();
                break;
            case R.id.navEndu:
                fragment = EnduranceGoalFragment.getInstance();
                break;
            case R.id.navWeight:
                fragment = WeightGoalFragment.getInstance();
                break;
            case R.id.navBody:
                fragment = BodyWeightFragment.getInstance();
                break;
            default:
                Log.e("navigation_error", "Tried to navigate to unknown fragment");
                return false;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        return true;
    }
}