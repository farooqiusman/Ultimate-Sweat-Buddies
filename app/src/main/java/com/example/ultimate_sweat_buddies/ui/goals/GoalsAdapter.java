package com.example.ultimate_sweat_buddies.ui.goals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.BodyWeightGoals;
import com.example.ultimate_sweat_buddies.data.model.EnduranceGoals;
import com.example.ultimate_sweat_buddies.data.model.Goal;
import com.example.ultimate_sweat_buddies.data.model.MiscGoals;
import com.example.ultimate_sweat_buddies.data.model.WeightGoals;

import java.util.List;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalsViewHolder> {
    List<Goal> goals;
    private Context mContext;
    private GoalsViewModel goalsViewModel;

    public GoalsAdapter(List<Goal> goals, Context mContext, GoalsViewModel goalsViewModel){
        this.goals =goals;
        this.mContext = mContext;
        this.goalsViewModel = goalsViewModel;
    }

    @NonNull
    @Override
    public GoalsAdapter.GoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item_layout,
                parent, false);
        return new GoalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsAdapter.GoalsViewHolder holder, int position) {
        Goal item = goals.get(position);
        if(item instanceof MiscGoals){
            holder.tvName.setText(item.getId());
            holder.tvDesc.setText(((MiscGoals) item).getDescription());
        }
        if(item instanceof EnduranceGoals){
            holder.tvName.setText(item.getId());
            holder.tvDesc.setText(item.getDeadLine());
        }
        if(item instanceof WeightGoals){
            holder.tvName.setText(((WeightGoals) item).getSets());
            holder.tvDesc.setText(((WeightGoals) item).getReps());
        }
        if(item instanceof BodyWeightGoals){
            holder.tvName.setText(item.getId());
            holder.tvDesc.setText(((BodyWeightGoals) item).getDescrtiption());
        }
    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public class GoalsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDesc;

        public GoalsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDeadlineComp);
        }
    }
}
