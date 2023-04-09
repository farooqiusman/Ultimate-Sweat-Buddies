package com.example.ultimate_sweat_buddies.ui.workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.plans.PlansAdapter;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    public interface WorkoutAdapterListener {
        void onExerciseCompleted(Exercise ex);
        void onEditClicked(Exercise ex);
        Exercise getValidEdits(Exercise ex);
    }

    private WorkoutAdapter.WorkoutAdapterListener listener;

    List<Exercise> exercises;
    private Context mContext;

    public WorkoutAdapter(List<Exercise> exercises, Context mContext) {
        this.exercises = exercises;
        this.mContext = mContext;
    }

    public void setListener(WorkoutAdapter.WorkoutAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_exercise_item_layout, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {
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

        holder.ibEdit.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            Exercise ex = exercises.get(pos);
            listener.onEditClicked(ex);
        });

        holder.ibComplete.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            Exercise ex = exercises.remove(pos);
            notifyItemRemoved(pos);

            // If there are valid edits, and the user clicked finish on the exercise being edited,
            // then use the edited info instead of the info in this exercise. Note that
            // hasValidEdits() checks if the exercise id we pass to it is the exercise being edited,
            // and returns null if there aren't valid edits
            Exercise editedExercise = listener.getValidEdits(ex);
            listener.onExerciseCompleted(editedExercise == null ? ex : editedExercise);
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public class WorkoutViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvDesc;
        private final ImageButton ibEdit;
        private final ImageButton ibComplete;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ibEdit = itemView.findViewById(R.id.ibEdit);
            ibComplete = itemView.findViewById(R.id.ibComplete);
        }
    }
}
