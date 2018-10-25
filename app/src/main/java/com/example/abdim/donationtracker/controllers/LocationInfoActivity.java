package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.Location;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationInfoActivity extends AppCompatActivity {
    TextView locationName;
    TextView locationType;
    TextView longitude;
    TextView latitude;
    TextView address;
    TextView phoneNumber;
    TextView websiteLink;
    Button toItemList;
    Button backButton;
    ListView locationInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

//        locationName = findViewById(R.id.nameText);
//        locationType = findViewById(R.id.typeText);
//        longitude = findViewById(R.id.longitudeText);
//        latitude = findViewById(R.id.latitudeText);
//        address = findViewById(R.id.addressText);
//        phoneNumber = findViewById(R.id.phoneText);
//        websiteLink = findViewById(R.id.websiteText);
//        backButton = findViewById(R.id.backButton);
        Intent intent = getIntent();

        Location receivedLocation = (Location) intent.getExtras().getSerializable("location");
//        locationName.setText(receivedLocation.getName());
//
//        locationType.setText(receivedLocation.getLocationType().toString());
//
//        longitude.setText(String.format("%5.3f", receivedLocation.getLongitude()));
//
//        latitude.setText(String.format("%5.3f", receivedLocation.getLatitude()));
//
//        address.setText(receivedLocation.getAddress());
//
//        phoneNumber.setText(receivedLocation.getPhoneNumber());
//
//        websiteLink.setText(receivedLocation.getWebsiteLink());


        locationInfoList = findViewById(R.id.locationInfoList);
        final List<String> locationPropertiesList = new ArrayList<>(Arrays.asList(
                "Name: " + receivedLocation.getName(),
                "Location Type: " + receivedLocation.getLocationType().toString(),
                "Latitude, Longitude: " + Double.toString(receivedLocation.getLatitude()) + ", " + Double.toString(receivedLocation.getLongitude()),
                "Address: " + receivedLocation.getAddress(),
                "Phone Number: " + receivedLocation.getPhoneNumber(),
                "Website: " + receivedLocation.getWebsiteLink()
        ));
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locationPropertiesList);
        locationInfoList.setAdapter(itemAdapter);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");


        toItemList = findViewById(R.id.lookAtInventoryButton);
        toItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationInfoActivity.this, ItemListActivity.class);

                intent.putExtra("location", getIntent().getExtras().getSerializable("location"));
                intent.putExtra("currentAccount", currentAccount);

                startActivity(intent);
                finish();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationInfoActivity.this, LocationListActivity.class);
                intent.putExtra("currentAccount", currentAccount);
                startActivity(intent);
                finish();
            }
        });

    }
}
