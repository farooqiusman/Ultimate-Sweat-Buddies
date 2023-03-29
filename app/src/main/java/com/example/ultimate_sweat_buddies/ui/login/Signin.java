package com.example.ultimate_sweat_buddies.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ultimate_sweat_buddies.MainActivity;
import com.example.ultimate_sweat_buddies.R;
import com.example.ultimate_sweat_buddies.api.apiclasses.GetStatus;
import com.example.ultimate_sweat_buddies.data.model.StoreLoginUser;
import com.example.ultimate_sweat_buddies.databinding.ActivitySigninBinding;
import com.example.ultimate_sweat_buddies.databinding.ActivitySignupBinding;

public class Signin extends AppCompatActivity {

    private ActivitySigninBinding binding;
    private SigninViewModel signinViewModel;
    private EditText userEmail, userPassword;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userEmail = binding.userEmail;
        userPassword = binding.editTextTextPassword2;
        signInButton = binding.signIn;
        signinViewModel = new ViewModelProvider(this).get(SigninViewModel.class);

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
                signinViewModel.signupDataChanged(userEmail.getText().toString(),
                        userPassword.getText().toString());
            }
        };
        userEmail.addTextChangedListener(afterTextChangedListener);
        userPassword.addTextChangedListener(afterTextChangedListener);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinViewModel.checkAuth();
            }
        });
        signinViewModel.getStatusLiveData().observe(this, new Observer<GetStatus>() {
            @Override
            public void onChanged(GetStatus getStatus) {
                String ret = getStatus.getResponse().split(":")[0];
                String username = getStatus.getResponse().split(":")[1];
                if(ret.equals("true")){
                    Toast.makeText(getApplicationContext(),
                            "Welcome: " + username + "! ",
                            Toast.LENGTH_SHORT).show();
                    StoreLoginUser.user.setUserName(username);
                    StoreLoginUser.user.setUserEmail(signinViewModel.getUserEmail());

                    Intent goToApp = new Intent(Signin.this, MainActivity.class);
                    startActivity(goToApp);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Invalid username or password!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}