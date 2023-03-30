package com.example.ultimate_sweat_buddies.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninViewModel extends ViewModel {
    private String userEmail, userPassword;
    private final APIInterface apiInterface;
    private final MutableLiveData<GetStatus> mutableLiveData = new MutableLiveData<>();

    public SigninViewModel(){
        this.userEmail = null;
        this.userPassword = null;
        apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }

    public void signupDataChanged(String useremail, String userpassword){
        this.userPassword = userpassword;
        this.userEmail = useremail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void checkAuth(){
        if(userEmail != null && userPassword != null){
            apiInterface.checkUsrAuth(this.userEmail, this.userPassword).enqueue(new Callback<GetStatus>() {
                @Override
                public void onResponse(@NonNull Call<GetStatus> call, @NonNull Response<GetStatus> response) {
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<GetStatus> call, @NonNull Throwable t) {

                }
            });
        }
    }
    public LiveData<GetStatus> getStatusLiveData(){
        return mutableLiveData;
    }


}
