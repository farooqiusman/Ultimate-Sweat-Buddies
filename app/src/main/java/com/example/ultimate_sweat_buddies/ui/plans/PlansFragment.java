package com.example.ultimate_sweat_buddies.ui.plans;




import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.Exercises;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlansFragment extends Fragment {


    private final PlansViewModel mViewModel = new PlansViewModel();

    public static PlansFragment instance;
    public static PlansFragment newInstance() {
        return new PlansFragment();
    }
    public static PlansFragment getInstance() {
        if (instance == null) instance = newInstance();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plans, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler_view);

        List<WorkoutPlan> data = null;
        try {
            data = mViewModel.getPlans("test@test.com").get();  // Waits for the future to return its result
        } catch (ExecutionException | InterruptedException e) {
            Log.d("error_getting_plans", "could not get plans");
            e.printStackTrace();
        }

        PlansAdapter adapter = new PlansAdapter(data, getContext(), mViewModel);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.add_btn);
        Intent intent = new Intent(getActivity(), AddEditPlanActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}