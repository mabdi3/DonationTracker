package com.example.abdim.donationtracker.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.LocationType;
import com.example.abdim.donationtracker.models.Locations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

public class LocationListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LocationListActivity";

    private ListView locationListView;
    private Button btnBack;

    private ArrayAdapter<Location> locationAdapter;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location_list);

        locationListView = findViewById(R.id.location_list);
        btnBack = findViewById(R.id.backButton);

        btnBack.setOnClickListener(this);

        // final List<Location> locationsAsList = Locations.getLocationsAsList();

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        locationListView.setAdapter(locationAdapter);


        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent locationInfoIntent = new Intent(
                        LocationListActivity.this, LocationInfoActivity.class);

                // Location location = locationsAsList.get(position);

                String locationName = locationListView.getItemAtPosition(position).toString();
                Log.d(TAG, locationName);

                locationInfoIntent.putExtra("locationName", locationName);
                startActivity(locationInfoIntent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference("locations");

        locationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationShot : dataSnapshot.getChildren()) {
                    Location locationValue = locationShot.getValue(Location.class);
                    locationAdapter.add(locationValue);
                    Log.d(TAG, "here is location name " + locationValue.getName());

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value" + error.toException());
            }
        });

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.backButton) {
            startActivity(new Intent(LocationListActivity.this, LoggedInActivity.class));
            finish();
        }
    }



}
