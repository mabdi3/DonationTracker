package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    FloatingActionButton toItemList;
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
        toItemList = findViewById(R.id.lookAtInventoryButton);
        Intent intent = getIntent();

        String receivedName = intent.getExtras().getString("name");
        locationName.setText(receivedName);

        String receivedType = intent.getStringExtra("locationType");
        locationType.setText(receivedType);

        String receivedLongitude = intent.getStringExtra("longitude");
        longitude.setText(receivedLongitude);

        String receivedLatitude = intent.getStringExtra("latitude");
        latitude.setText(receivedLatitude);

        String receivedAddress = intent.getStringExtra("address");
        address.setText(receivedAddress);

        String receivedPhone = intent.getStringExtra("phoneNumber");
        phoneNumber.setText(receivedPhone);

        String receivedWebsite = intent.getStringExtra("websiteLink");
        websiteLink.setText(receivedWebsite);



        toItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationInfoActivity.this, ItemListActivity.class);
                Location location = (Location) intent.getExtras().getSerializable("location");
                intent.putExtra("location", (Serializable) location);
                startActivity(intent);
                finish();
            }
        });

    }
}
