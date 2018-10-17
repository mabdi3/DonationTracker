package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;

public class HomeActivity extends AppCompatActivity {
    private Button login;
    private TextView welcome;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        login = findViewById(R.id.btnLogin);
        welcome = findViewById(R.id.textWelcome);
        welcome.setText("Welcome to Donation Tracker");
        Register = (Button)findViewById(R.id.registerBtn);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(login);
                // finish();
            }
        });

    }
}
