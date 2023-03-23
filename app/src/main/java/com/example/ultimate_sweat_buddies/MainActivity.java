package com.example.ultimate_sweat_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ultimate_sweat_buddies.databinding.ActivityMainBinding;
import com.example.ultimate_sweat_buddies.ui.exercises.ExercisesFragment;
import com.example.ultimate_sweat_buddies.ui.goals.GoalsFragment;
import com.example.ultimate_sweat_buddies.ui.history.HistoryFragment;
import com.example.ultimate_sweat_buddies.ui.plans.PlansFragment;
import com.example.ultimate_sweat_buddies.ui.profile.ProfileFragment;
import com.example.ultimate_sweat_buddies.ui.workout.WorkoutFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ActivityMainBinding binds to activity_login.xml, and we can access the elements by their
        // id's using binding.<id>
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup navigation event for the primary navigation menu
        // (Note: NavigationBarView is abstract, and BottomNavigationView extends it)
        final NavigationBarView bnvPrimaryNav = binding.bnvPrimaryNav;
        bnvPrimaryNav.setOnItemSelectedListener(item -> switchFragment(item.getItemId()));
    }

    // Creates the secondary menu in the app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.secondary_nav_menu, menu);
        return true;
    }

    // On Item Selected Listener for the secondary menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return switchFragment(item.getItemId());
    }


    // Both the bottom navbar and the navigation options from the app bar use this function
    @SuppressLint("NonConstantResourceId")
    private boolean switchFragment(int fragmentId) {
        // Get the fragment for the clicked on navigation item
        Fragment fragment;
        switch (fragmentId) {
            case R.id.navWorkout:
                fragment = new WorkoutFragment();
                break;
            case R.id.navPlans:
                fragment = new PlansFragment();
                break;
            case R.id.navExercises:
                fragment = new ExercisesFragment();
                break;
            case R.id.navGoals:
                fragment = new GoalsFragment();
                break;
            case R.id.navHistory:
                fragment = new HistoryFragment();
                break;
            case R.id.navProfile:
                fragment = new ProfileFragment();
                break;
            default:
                Log.e("navigation_error", "Tried to navigate to unknown fragment");
                return false;
        }

        // Show the selected fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flBody, fragment);
        fragmentTransaction.commit();
        return true;
    }
}