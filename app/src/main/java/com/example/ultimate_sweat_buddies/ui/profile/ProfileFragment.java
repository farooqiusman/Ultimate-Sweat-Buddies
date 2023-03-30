package com.example.ultimate_sweat_buddies.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.ui.login.LoginActivity;


public class ProfileFragment extends Fragment {
    private ProfileViewModel mViewModel;
    private static ProfileFragment instance;
    private TextView tv_wel, tv_user, tv_email;
    private Button edit_profile_btn, logout_btn;


    public static ProfileFragment getInstance() {
        if (instance == null) instance = new ProfileFragment();
        return instance;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tv_wel = view.findViewById(R.id.tv_wel);
        tv_wel.setText("Welcome, " + StoreLoginUser.user.getUserName() + "!");
        tv_user = view.findViewById(R.id.tv_usr);
        tv_user.setText("Username: " + StoreLoginUser.user.getUserName());
        tv_email = view.findViewById(R.id.tv_email);
        tv_email.setText("Email: " + StoreLoginUser.user.getUserEmail());
        Intent intent1 = new Intent(getActivity(), EditProfile.class);
        edit_profile_btn = view.findViewById(R.id.edit_pro);
        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
        logout_btn = view.findViewById(R.id.logout_btn);
        Intent intent2 = new Intent(getActivity(), LoginActivity.class);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((intent2));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

}