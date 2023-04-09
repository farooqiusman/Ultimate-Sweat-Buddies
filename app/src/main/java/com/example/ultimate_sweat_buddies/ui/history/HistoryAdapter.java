package com.example.ultimate_sweat_buddies.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.plans.PlansViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    List<String> workoutLogLines;

    public HistoryAdapter(List<String> workoutLogLines) {
        this.workoutLogLines = workoutLogLines;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        String exerciseItem = workoutLogLines.get(position);
        String[] tokens = exerciseItem.split(",");

        // Each line is composed of "type,name,sets,reps,weight" or "type,name,time"
        holder.tvExerciseName.setText(tokens[1]);
        if (tokens[0].equals("weight")) {
            holder.tvExerciseDesc.setText(String.format("Sets: %s, Reps: %s, Weight: %s",
                    tokens[2], tokens[3], tokens[4]));
        } else if (tokens[0].equals("endurance")) {
            holder.tvExerciseDesc.setText(String.format("Time: %s", tokens[2]));
        }
    }

    @Override
    public int getItemCount() {
        return workoutLogLines.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvExerciseName;
        private final TextView tvExerciseDesc;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExerciseName = itemView.findViewById(R.id.tvExerciseName);
            tvExerciseDesc = itemView.findViewById(R.id.tvExerciseDesc);
        }
    }
}
