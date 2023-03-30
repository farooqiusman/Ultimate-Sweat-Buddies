package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExercisesFragment extends Fragment {

    private final ExercisesViewModel mViewModel = new ExercisesViewModel();
    private static ExercisesFragment instance;

    public static ExercisesFragment getInstance() {
        if (instance == null) instance = new ExercisesFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.Recycler_view);

        List<Exercise> data = null;
        try {
            data = mViewModel.getExercises("test@test.com").get();  // Waits for the future to return its result
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ExercisesAdapter adapter = new ExercisesAdapter(data, getContext(), ExercisesAdapter.ExerciseListType.EDIT_DELETE);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FloatingActionButton fab = view.findViewById(R.id.add_btn);
        Intent intent = new Intent(getActivity(), AddExercisesActivity.class);
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