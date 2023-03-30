package com.example.ultimate_sweat_buddies.ui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimate_sweat_buddies.R;

public class EditProfile extends AppCompatActivity {
    private Button update_usr_btn, update_email_btn, update_pass_btn;
    private TextView tv_usr, tv_email, tv_pass;
    private EditText et_usr, et_email, et_pass;
    private Button go_usr, go_email, go_pass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        update_usr_btn = findViewById(R.id.updt_usr);
        update_email_btn = findViewById(R.id.updt_email);
        update_pass_btn = findViewById(R.id.updt_pass);
        tv_usr = findViewById(R.id.tv_nw_usr);
        tv_email = findViewById(R.id.tv_nw_email);
        tv_pass = findViewById(R.id.tv_nw_pass);
        et_usr = findViewById(R.id.et_nw_usr);
        et_email = findViewById(R.id.et_nw_email);
        et_pass = findViewById(R.id.et_nw_pass);
        go_usr = findViewById(R.id.go_btn1);
        go_email = findViewById(R.id.go_btn2);
        go_pass = findViewById(R.id.go_btn3);

        update_usr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_usr.setVisibility(View.VISIBLE);
                et_usr.setVisibility(View.VISIBLE);
                go_usr.setVisibility(View.VISIBLE);
            }
        });
        update_email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_email.setVisibility(View.VISIBLE);
                et_email.setVisibility(View.VISIBLE);
                go_email.setVisibility(View.VISIBLE);
            }
        });
        update_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_pass.setVisibility(View.VISIBLE);
                et_pass.setVisibility(View.VISIBLE);
                go_pass.setVisibility(View.VISIBLE);
            }
        });

    }
}
