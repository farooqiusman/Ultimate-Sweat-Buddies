package com.example.ultimate_sweat_buddies.ui.plans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.Exercise;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;
import com.example.ultimate_sweat_buddies.ui.exercises.ExercisesAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlansViewHolder> {

    public interface PlansAdapterListener { // Used in AddEditPlanActivity to move exercises between two exercises adapters
        void onPlanSelected(WorkoutPlan plan);
    }

    private PlansAdapter.PlansAdapterListener listener;

    List<WorkoutPlan> plans;
    private final Context mContext;
    private final PlansViewModel mViewModel;

    public enum PlanListType {
        EDIT_DELETE, SELECT     // Plans page allows editing and deleting items, but the workout
                                // page allows you to select a plan
    }
    private final PlanListType type;

    public PlansAdapter(List<WorkoutPlan> plans, Context mContext, PlansViewModel mViewModel, PlanListType type) {
        this.plans = plans;
        this.mContext = mContext;
        this.mViewModel = mViewModel;
        this.type = type;
    }

    public void setListener(PlansAdapter.PlansAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlansAdapter.PlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (type) {
            case EDIT_DELETE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_plan_item_layout, parent, false);
                break;
            case SELECT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_workout_plan_item_layout, parent, false);
                break;
        }
        assert view != null;
        return new PlansViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.PlansViewHolder holder, int position) {
        WorkoutPlan item = plans.get(position);
        holder.tvName.setText(item.getTitle());
        holder.tvDaysOfWeek.setText(mContext.getString(R.string.days_of_week, item.getDaysOfWeek()));

        switch (type) {
            case EDIT_DELETE:
                // edit and delete button listeners
                holder.ibButton1.setOnClickListener(view -> {

                });

                holder.ibButton2.setOnClickListener(view -> {
                    int pos = holder.getAdapterPosition();  // Use this in events since position may not be fixed
                    try {
                        boolean success = mViewModel.deletePlan(plans.get(pos).getUserEmail(), plans.get(pos).getTitle()).get();
                        if (success) {
                            plans.remove(pos);
                            notifyItemRemoved(pos);
                        }
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                break;
            case SELECT:
                // Select button listener
                holder.ibButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        WorkoutPlan plan = plans.get(pos);
                        listener.onPlanSelected(plan);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvDaysOfWeek;
        private ImageButton ibButton1;
        private ImageButton ibButton2;

        public PlansViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDaysOfWeek = itemView.findViewById(R.id.tvDaysOfWeek);
            
            switch (type) {
                case EDIT_DELETE:
                    ibButton1 = itemView.findViewById(R.id.ibEdit);
                    ibButton2 = itemView.findViewById(R.id.ibDelete);
                    break;
                case SELECT:
                    ibButton1 = itemView.findViewById(R.id.ibSelect);
                    break;
            }
        }
    }
}
