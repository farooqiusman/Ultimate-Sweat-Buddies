package com.example.ultimate_sweat_buddies.ui.login;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import com.example.ultimate_sweat_buddies.api.APIInterface;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.RetrofitInstance;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetUserEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupViewModel extends ViewModel {
    private String userName, userEmail, userPassword;
    private APIInterface apiInterface;

    public SignupViewModel(){
        this.userName = null;
        this.userEmail = null;
        this.userPassword = null;
        apiInterface = RetrofitInstance.getRetrofit().create(APIInterface.class);
    }

    public void signupDataChanged(String username, String useremail, String userpassword){
        userName = username;
        userPassword = userpassword;
        userEmail = useremail;
    }

    public String CheckInputs(){
        if(!isUsernameValid(userName)){
            return "username";
        }
        if(!isUserEmailValid(userEmail)){
            return "email";
        }
        if(!isPasswordValid(userPassword)){
            return "password";
        }
        return "pass";
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

    public String getUserName() {
        return userName;
    }

    public void apiCall(){
        GetUserEmail user_email = new GetUserEmail(userEmail);
        apiInterface.getUserEmail(user_email).enqueue(new Callback<GetUserEmail>() {
            @Override
            public void onResponse(Call<GetUserEmail> call, Response<GetUserEmail> response) {
                assert response.body() != null;
                Log.d("response", String.valueOf(response.body().getResponse()));
            }

            @Override
            public void onFailure(Call<GetUserEmail> call, Throwable t) {
                Log.d("response", "Response Failed with: " + t.toString());
            }
        });
    }
}
