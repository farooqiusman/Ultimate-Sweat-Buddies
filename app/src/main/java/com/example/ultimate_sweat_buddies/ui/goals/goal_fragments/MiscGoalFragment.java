package com.example.ultimate_sweat_buddies.ui.goals.goal_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ultimate_sweat_buddies.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiscGoalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiscGoalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static MiscGoalFragment instance;

    public MiscGoalFragment() {
        // Required empty public constructor
    }

    public static MiscGoalFragment newInstance(){
        return new MiscGoalFragment();
    }
    public static MiscGoalFragment getInstance(){
        if (instance == null){
            instance = newInstance();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_misc_goal, container, false);
        return view;
    }
}