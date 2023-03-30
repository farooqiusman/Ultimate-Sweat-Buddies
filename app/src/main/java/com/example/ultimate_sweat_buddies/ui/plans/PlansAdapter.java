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
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlansViewHolder> {

    List<WorkoutPlan> plans;
    private final Context mContext;
    private final PlansViewModel mViewModel;

    public PlansAdapter(List<WorkoutPlan> plans, Context mContext, PlansViewModel mViewModel) {
        this.plans = plans;
        this.mContext = mContext;
        this.mViewModel = mViewModel;
    }

    @NonNull
    @Override
    public PlansAdapter.PlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_plan_item_layout, parent, false);
        return new PlansViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.PlansViewHolder holder, int position) {
        WorkoutPlan item = plans.get(position);
        holder.tvName.setText(item.getTitle());
        holder.tvDaysOfWeek.setText(mContext.getString(R.string.days_of_week, item.getDaysOfWeek()));

        holder.ibEdit.setOnClickListener(view -> {

        });

        holder.ibDelete.setOnClickListener(view -> {
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
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvDaysOfWeek;
        private final ImageButton ibEdit;
        private final ImageButton ibDelete;

        public PlansViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDaysOfWeek = itemView.findViewById(R.id.tvDaysOfWeek);
            ibEdit = itemView.findViewById(R.id.ibEdit);
            ibDelete = itemView.findViewById(R.id.ibDelete);
        }
    }
}
