package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.Exercises;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder> {

    List<Exercises> exercises;
    private Context mContext;

    public ExercisesAdapter(List<Exercises> exercises, Context mContext) {
        this.exercises = exercises;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ExercisesAdapter.ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_exercises, parent, false);
        ExercisesViewHolder exercisesViewHolder = new ExercisesViewHolder(view);
        return exercisesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesAdapter.ExercisesViewHolder holder, int position) {
        Exercises item = exercises.get(position);
        holder.exerciseTypeTextView.setText(item.getExerciseType());
        holder.exerDesc.setText(item.getWorkoutPlanTitle());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ExercisesViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseTypeTextView;
        private TextView exerDesc;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTypeTextView = itemView.findViewById(R.id.exerciseTypeTextView);
            exerDesc = itemView.findViewById(R.id.exerciseDescriptionTextView);
        }

        public void bind(String exercise) {
            exerciseTypeTextView.setText(exercise);
        }
    }
}
