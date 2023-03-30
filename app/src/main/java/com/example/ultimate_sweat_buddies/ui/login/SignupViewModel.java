package com.example.ultimate_sweat_buddies.ui.login;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.NewUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends ViewModel {
    private String userName, userEmail, userPassword;
    private final APIInterface apiInterface;
    private final MutableLiveData<GetStatus> myresponse = new MutableLiveData<>();
    private final MutableLiveData<NewUser> newUserResponse = new MutableLiveData<>();

    public SignupViewModel(){
        this.userName = null;
        this.userEmail = null;
        this.userPassword = null;
        this.apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }

    public void signupDataChanged(String username, String useremail, String userpassword){
        userName = username;
        userPassword = userpassword;
        userEmail = useremail;
    }

    public boolean CheckInputs(Context context){
        if(!isUsernameValid(userName)){
            Toast.makeText(context,
                    "Invalid username!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isUserEmailValid(userEmail)){
            Toast.makeText(context,
                    "Invalid Email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isPasswordValid(userPassword)){
            Toast.makeText(context,
                    "Invalid Password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isUserEmailValid(String userEmail) {
        if (userEmail == null) {
            return false;
        }
        if (userEmail.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
        } else {
            return false;
        }
    }
    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isUsernameValid(String userName){
        return userName != null && userName.trim().length() > 3;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LiveData<GetStatus> getStatusLiveData(){
        return myresponse;
    }

    public LiveData<NewUser> getNewUserLiveData(){
        return newUserResponse;
    }

    public void makeApiCall(){
        apiInterface.getUserEmail(userEmail).enqueue(new Callback<GetStatus>() {
            @Override
            public void onResponse(@NonNull Call<GetStatus> call, @NonNull Response<GetStatus> response) {
                myresponse.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<GetStatus> call, @NonNull Throwable t) {

            }
        });
    }

    public void postNewUser(){
        NewUser newUser = new NewUser(userEmail, userPassword, userName);
        apiInterface.newUser(newUser).enqueue(new Callback<NewUser>() {
            @Override
            public void onResponse(@NonNull Call<NewUser> call, @NonNull Response<NewUser> response) {
                newUserResponse.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<NewUser> call, @NonNull Throwable t) {

            }
        });
    }
}
