package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;

import org.w3c.dom.Text;

import java.io.Serializable;

public class LocationInfoActivity extends AppCompatActivity {
    TextView locationName;
    TextView locationType;
    TextView longitude;
    TextView latitude;
    TextView address;
    TextView phoneNumber;
    TextView websiteLink;
    Button toItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        locationName = findViewById(R.id.nameText);
        locationType = findViewById(R.id.typeText);
        longitude = findViewById(R.id.longitudeText);
        latitude = findViewById(R.id.latitudeText);
        address = findViewById(R.id.addressText);
        phoneNumber = findViewById(R.id.phoneText);
        websiteLink = findViewById(R.id.websiteText);
        Intent intent = getIntent();

        Location receivedLocation = (Location) intent.getExtras().getSerializable("location");
        locationName.setText(receivedLocation.getName());

        locationType.setText(receivedLocation.getLocationType().toString());

        longitude.setText(String.format("%5.3f", receivedLocation.getLongitude()));

        latitude.setText(String.format("%5.3f", receivedLocation.getLatitude()));

        address.setText(receivedLocation.getAddress());

        phoneNumber.setText(receivedLocation.getPhoneNumber());

        websiteLink.setText(receivedLocation.getWebsiteLink());

        toItemList = findViewById(R.id.lookAtInventoryButton);
        toItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationInfoActivity.this, ItemListActivity.class);
                intent.putExtra("location", getIntent().getExtras().getSerializable("location"));
                startActivity(intent);
                finish();
            }
        });

    }
}
