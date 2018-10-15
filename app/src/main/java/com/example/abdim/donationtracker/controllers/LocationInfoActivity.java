package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;

public class LocationInfoActivity extends AppCompatActivity {
    TextView locationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        locationName = findViewById(R.id.location_name);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("name");

        locationName.setText(receivedName);

    }
}
