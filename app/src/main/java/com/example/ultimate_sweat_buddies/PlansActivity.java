package com.example.ultimate_sweat_buddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlansActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PlanAdapter mAdapter;
    private List<Plan> plans = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        initData();


        initRecyclerView();

        mAdapter.setOnItemClickListener(new PlanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here
                Toast.makeText(PlansActivity.this, "Item clicked at position " + position, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initData() {

        plans.add(new Plan("Plan 1", "Plan 1", "Plan 1"));
        plans.add(new Plan("Plan 2", "Plan 2", "Plan 2"));
        plans.add(new Plan("Plan 3", "Plan 3", "Plan 3"));
        plans.add(new Plan("Plan 4", "Plan 4", "Plan 4"));
        plans.add(new Plan("Plan 5", "Plan 5", "Plan 5"));
        plans.add(new Plan("Plan 6", "Plan 6", "Plan 6"));
        plans.add(new Plan("Plan 7", "Plan 7", "Plan 7"));
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new PlanAdapter(plans, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}