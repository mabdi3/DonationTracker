package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LocationInfoActivity";
    private Button btnToItemList;
    private Button btnBack;
    private ListView locationInfoListView;
    private String locationName;

    private ArrayAdapter<String> locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        locationInfoListView = findViewById(R.id.locationInfoList);
        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        locationInfoListView.setAdapter(locationAdapter);

        btnToItemList = findViewById(R.id.lookAtInventoryButton);
        btnBack = findViewById(R.id.backButton);
        btnToItemList.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        // get intent information
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                locationName = null;
            } else {
                locationName = extras.getString("locationName");
            }
        } else {
            locationName = (String) savedInstanceState.getSerializable("locationName");
        }

        // Location receivedLocation = Locations.getLocationsAsList().get(intents.getExtras().getInt("location"));
        /*
        final List<String> locationPropertiesList = new ArrayList<>(Arrays.asList(
                "Name: " + receivedLocation.getName(),
                "Location Type: " + receivedLocation.getLocationType().toString(),
                "Latitude, Longitude: " + Double.toString(receivedLocation.getLatitude()) + ", " + Double.toString(receivedLocation.getLongitude()),
                "Address: " + receivedLocation.getAddress(),
                "Phone Number: " + receivedLocation.getPhoneNumber(),
                "Website: " + receivedLocation.getWebsiteLink()
        ));
        */



        btnToItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                // Intent intent = new Intent(LocationInfoActivity.this, ItemListActivity.class);

                intent.putExtra("location", intents.getExtras().getInt("location"));

                startActivity(intent);
                finish();
                */
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();

        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference("location");
        locationsRef.orderByChild("name").equalTo(locationName).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Log.d(TAG, "heyo " + dataSnapshot.getKey());
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String string) {
            }
        });
        /*
        locationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationShot : dataSnapshot.getChildren()) {
                    Location locationValue = locationShot.getValue(Location.class);
                    locationAdapter.add();
                    Log.d(TAG, "here is location name " + locationValue.getName());

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value" + error.toException());
            }
        });
        */

    }
    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.backButton) {
            startActivity(new Intent(LocationInfoActivity.this, LocationListActivity.class));
            finish();
        } else if (i == R.id.lookAtInventoryButton) {

        }
    }
}
