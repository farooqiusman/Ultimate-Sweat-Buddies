package com.example.ultimate_sweat_buddies.api.apiclasses;

import com.example.ultimate_sweat_buddies.data.model.BodyWeightGoals;
import com.example.ultimate_sweat_buddies.data.model.EnduranceGoals;
import com.example.ultimate_sweat_buddies.data.model.MiscGoals;
import com.example.ultimate_sweat_buddies.data.model.WeightGoals;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllGoals {
    @SerializedName("misc_goals")
    private List<MiscGoals> miscGoalsList;

    @SerializedName("endurance_goals")
    private List<EnduranceGoals> enduranceGoalsList;

    @SerializedName("weight_goals")
    private List<WeightGoals> weightGoalsList;

    @SerializedName("body_weight_goals")
    private List<BodyWeightGoals> bodyWeightGoalsList;

    public GetAllGoals(List<MiscGoals> miscGoalsList, List<EnduranceGoals> enduranceGoalsList,
                       List<WeightGoals> weightGoalsList, List<BodyWeightGoals> bodyWeightGoalsList){
        this.miscGoalsList = miscGoalsList;
        this.enduranceGoalsList = enduranceGoalsList;
        this.weightGoalsList = weightGoalsList;
        this.bodyWeightGoalsList = bodyWeightGoalsList;
    }

    public List<MiscGoals> getMiscGoalsList() {
        return miscGoalsList;
    }

    public void setMiscGoalsList(List<MiscGoals> miscGoalsList) {
        this.miscGoalsList = miscGoalsList;
    }

    public List<EnduranceGoals> getEnduranceGoalsList() {
        return enduranceGoalsList;
    }

    public void setEnduranceGoalsList(List<EnduranceGoals> enduranceGoalsList) {
        this.enduranceGoalsList = enduranceGoalsList;
    }

    public List<WeightGoals> getWeightGoalsList() {
        return weightGoalsList;
    }

    public void setWeightGoalsList(List<WeightGoals> weightGoalsList) {
        this.weightGoalsList = weightGoalsList;
    }

    public List<BodyWeightGoals> getBodyWeightGoalsList() {
        return bodyWeightGoalsList;
    }

    public void setBodyWeightGoalsList(List<BodyWeightGoals> bodyWeightGoalsList) {
        this.bodyWeightGoalsList = bodyWeightGoalsList;
    }
}
