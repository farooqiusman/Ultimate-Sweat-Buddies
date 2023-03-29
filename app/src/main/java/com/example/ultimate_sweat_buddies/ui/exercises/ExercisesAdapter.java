package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder> {

    List<Exercise> exercises;
    private Context mContext;

    public ExercisesAdapter(List<Exercise> exercises, Context mContext) {
        this.exercises = exercises;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ExercisesAdapter.ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item_layout, parent, false);
        return new ExercisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesAdapter.ExercisesViewHolder holder, int position) {
        Exercise item = exercises.get(position);
        holder.tvName.setText(item.getName());
        if (item instanceof WeightExercise) {
            WeightExercise exercise = (WeightExercise) item;
            String desc = String.format("Sets: %d, Reps: %d, Weight: %d", exercise.getSets(), exercise.getReps(), exercise.getWeight());
            holder.tvDesc.setText(desc);
        } else if (item instanceof EnduranceExercise) {
            EnduranceExercise exercise = (EnduranceExercise) item;
            String desc = String.format("Time: %s", exercise.getTime());
            holder.tvDesc.setText(desc);
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ExercisesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDesc;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDaysOfWeek);
        }
    }
}
