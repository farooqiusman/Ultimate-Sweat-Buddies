package com.example.ultimate_sweat_buddies.ui.login;

import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ultimate_sweat_buddies.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set theme to dark
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // ActivityLoginBinding binds to activity_login.xml, and we can access the elements by their
        // id's using binding.<id>
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button signUpButton = binding.signup;
        final Button signInButton = binding.signin;

        signUpButton.setOnClickListener(view -> {
            Intent signup = new Intent(LoginActivity.this, Signup.class);
            startActivity(signup);
        });

        signInButton.setOnClickListener(view -> {
            Intent signIn = new Intent(LoginActivity.this, Signin.class);
            startActivity(signIn);
        });
    }
}