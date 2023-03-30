package com.example.ultimate_sweat_buddies.ui.profile;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;

public class ProfileViewModel extends ViewModel {


    private APIInterface apiInterface;

    public ProfileViewModel() {
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }


}