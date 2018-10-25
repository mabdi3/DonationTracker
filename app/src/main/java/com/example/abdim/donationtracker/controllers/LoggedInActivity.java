package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;

public class LoggedInActivity extends AppCompatActivity {

    private Button toList;
    private Button logout;

    private boolean shouldAllowBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");

        toList = findViewById(R.id.btnToList);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add static boolean to track if location list activity is opened for the first time

                Intent goList = new Intent(LoggedInActivity.this, LocationListActivity.class);

                goList.putExtra("currentAccount", currentAccount);

                startActivity(goList);
                finish();

            }
        });

        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goList = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(goList);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (shouldAllowBack) {
            super.onBackPressed();
        }
    }

}
