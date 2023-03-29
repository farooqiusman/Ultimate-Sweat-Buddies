package com.example.ultimate_sweat_buddies.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.api.apiclasses.NewUser;
import com.example.ultimate_sweat_buddies.databinding.ActivitySignupBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private SignupViewModel signupViewModel;

    private EditText username, email, password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        username = binding.editTextTextPersonName;
        email = binding.editTextTextEmailAddress;
        password = binding.editTextTextPassword;
        register = binding.registerButton;
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable editable) {
                signupViewModel.signupDataChanged(username.getText().toString(),
                        email.getText().toString(), password.getText().toString());
            }
        };
        username.addTextChangedListener(afterTextChangedListener);
        email.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signupViewModel.CheckInputs(getApplicationContext())) {
                    signupViewModel.makeApiCall();
                }
            }
        });
        signupViewModel.getStatusLiveData().observe(this, new Observer<GetStatus>() {
            @Override
            public void onChanged(GetStatus getStatus) {
                if(Integer.parseInt(getStatus.getResponse()) >= 1){
                    Toast.makeText(getApplicationContext(),
                            "Error " + signupViewModel.getUserEmail()
                                    + " exits!", Toast.LENGTH_SHORT).show();
                    Intent backToMain = new Intent(Signup.this, LoginActivity.class);
                    startActivity(backToMain);
                }else{
                    signupViewModel.postNewUser();
                    signupViewModel.getNewUserLiveData().observe(Signup.this, new Observer<NewUser>() {
                        @Override
                        public void onChanged(NewUser newUser) {
                            Intent signInPage = new Intent(Signup.this, Signin.class);
                            startActivity(signInPage);
                        }
                    });
                }
            }
        });
    }


}