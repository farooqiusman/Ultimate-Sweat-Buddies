package com.example.ultimate_sweat_buddies.ui.exercises;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.EnduranceExercise;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WeightExercise;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder> {

    public interface ExercisesAdapterListener { // Used in AddEditPlanActivity to move exercises between two exercises adapters
        void onExerciseRemoved(Exercise ex, ExerciseListType type);
    }



    private ExercisesAdapterListener listener;
    List<Exercise> exercises;

    private Context mContext;
    public enum ExerciseListType {
        EDIT_DELETE, ADD, REMOVE    // Exercises page allows editing and deleting items, but the workout plans
                                    // edit page has two lists of exercises. One with added exercises that can
                                    // be "REMOVEd" and one with unadded exercises that can be "ADDed"
    }
    private ExerciseListType type;
    private final ExercisesViewModel mViewModel = new ExercisesViewModel();


    public ExercisesAdapter(List<Exercise> exercises, Context mContext, ExerciseListType type) {
        this.exercises = exercises;
        this.mContext = mContext;
        this.type = type;
    }





    public void setListener(ExercisesAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExercisesAdapter.ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (type) {
            case EDIT_DELETE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item_layout_type_1, parent, false);
                break;
            case ADD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item_layout_type_2, parent, false);
                break;
            case REMOVE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item_layout_type_3, parent, false);
                break;
        }
        assert view != null;
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

        switch (type) {
            case EDIT_DELETE:
                // edit and delete button listeners
                holder.ibButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();

                        Toast.makeText(mContext, "Exercise deleted", Toast.LENGTH_SHORT).show();
                        try{
                            String exerciseType = exercises.get(pos) instanceof WeightExercise ? "weight" : "endurance";

                            Boolean success = mViewModel.deleteExercise(exercises.get(pos).getId(), exerciseType).get();
                            if (success) {
                                Exercise ex = exercises.remove(pos);

                                notifyItemRemoved(pos);
                                if(listener != null)
                                    listener.onExerciseRemoved(ex, type);
                            }


                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;

            case ADD:
                holder.ibButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Exercise ex = exercises.remove(pos);
                        notifyItemRemoved(pos);
                        listener.onExerciseRemoved(ex, type);
                    }
                });
                break;
            case REMOVE:
                holder.ibButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Exercise ex = exercises.remove(pos);
                        notifyItemRemoved(pos);
                        listener.onExerciseRemoved(ex, type);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void addExercise(Exercise ex) {
        exercises.add(ex);
        notifyItemInserted(getItemCount() - 1);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public class ExercisesViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDesc;
        private ImageButton ibButton1;
        private ImageButton ibButton2;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDaysOfWeek);
            switch (type) {
                case EDIT_DELETE:
                    ibButton1 = itemView.findViewById(R.id.ibEdit);
                    ibButton2 = itemView.findViewById(R.id.ibDelete);
                    break;
                case ADD:
                    ibButton1 = itemView.findViewById(R.id.ibAdd);
                    break;
                case REMOVE:
                    ibButton1 = itemView.findViewById(R.id.ibRemove);
                    break;
            }
        }
    }
}
