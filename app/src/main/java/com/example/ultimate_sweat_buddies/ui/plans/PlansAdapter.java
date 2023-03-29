package com.example.ultimate_sweat_buddies.ui.plans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.WorkoutPlan;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlansViewHolder> {

    List<WorkoutPlan> plans;
    private Context mContext;

    public PlansAdapter(List<WorkoutPlan> exercises, Context mContext) {
        this.plans = exercises;
        this.mContext = mContext;
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
        holder.tvDaysOfWeek.setText("Days of Week: " + item.getDaysOfWeek());
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDaysOfWeek;

        public PlansViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDaysOfWeek = itemView.findViewById(R.id.tvDaysOfWeek);
        }
    }
}
