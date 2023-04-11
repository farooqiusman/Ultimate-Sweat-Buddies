package com.example.ultimate_sweat_buddies.ui.exercises;

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
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.ui.plans.AddEditPlanActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExercisesFragment extends Fragment implements ExercisesAdapter.ExercisesAdapterEditListener {

    private final ExercisesViewModel mViewModel = new ExercisesViewModel();
    private static ExercisesFragment instance;

    private ExercisesAdapter adapter;
    private RecyclerView recyclerView;

    public static ExercisesFragment getInstance() {
        if (instance == null) instance = new ExercisesFragment();
        return instance;
    }

    private String email = StoreLoginUser.user.getUserEmail();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        recyclerView = view.findViewById(R.id.Recycler_view);
        Log.e("res", "onCreateView: " );
        setupExercisesAdapter();

        FloatingActionButton fab = view.findViewById(R.id.add_btn);
        Intent intent = new Intent(getActivity(), AddEditExercisesActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        return view;
    }

    public void setupExercisesAdapter() {
        List<Exercise> data = null;
        try {
            data = mViewModel.getExercises(email).get();  // Waits for the future to return its result
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ExercisesAdapter(data, getContext(), ExercisesAdapter.ExerciseListType.EDIT_DELETE);
        adapter.setEditListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("res", "onResume: ");
        setupExercisesAdapter();    // Refresh the exercises list
    }

    @Override
    public void onEditExercise(Exercise ex) {
        Intent editExerciseIntent = new Intent(getContext(), AddEditExercisesActivity.class);
        editExerciseIntent.putExtra("update_type", "edit");
        editExerciseIntent.putExtra("name", ex.getName());
        editExerciseIntent.putExtra("id", ex.getId());
        if(ex instanceof WeightExercise){
            WeightExercise we = (WeightExercise)ex;
            editExerciseIntent.putExtra("exercise_type", "weight");
            editExerciseIntent.putExtra("sets", we.getSets());
            editExerciseIntent.putExtra("reps", we.getReps());
            editExerciseIntent.putExtra("weight", we.getWeight());

        } else if (ex instanceof EnduranceExercise) {
            EnduranceExercise ee = (EnduranceExercise) ex;
            editExerciseIntent.putExtra("exercise_type", "endurance");
            // Parse the hh:MM:ss string and populate the EditTexts
            String time = ee.getTime();
            int firstColonIdx = time.indexOf(':');
            int secondColonIdx = (firstColonIdx + 1) + time.substring(firstColonIdx + 1).indexOf(':');
            editExerciseIntent.putExtra("hours", time.substring(0, firstColonIdx));
            editExerciseIntent.putExtra("minutes" , time.substring(firstColonIdx + 1, secondColonIdx));
            editExerciseIntent.putExtra("seconds", time.substring(secondColonIdx + 1));
        }
        startActivity(editExerciseIntent);

    }
}