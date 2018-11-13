package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressWarnings("ALL")
public class LocationInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LocationInfoActivity";
    private String locationName;
    private String locationKey;
    private ArrayAdapter<String> locationAdapter;
    private DatabaseReference locationInfoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);

        ListView locationInfoListView = findViewById(R.id.locationInfoList);
        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        locationInfoListView.setAdapter(locationAdapter);

        Button btnToItemList = findViewById(R.id.lookAtInventoryButton);
        Button btnBack = findViewById(R.id.backButton);
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

        setLocationInfo();
    }

    private void setLocationKey(String key) {
        locationKey = key;
    }

    private void setLocationInfo() {
        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference("locations");

        Log.d(TAG, "location name is " + locationName);

        locationsRef.orderByChild("name").equalTo(locationName).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                setLocationKey(dataSnapshot.getKey());

                locationInfoRef = FirebaseDatabase.getInstance().getReference("locations/" + locationKey);

                locationInfoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "locationKey" + locationKey);
                        Location locationValue = dataSnapshot.getValue(Location.class);
                        Log.d(TAG, "locationName " + locationName);

                        locationAdapter.add("Location Type: " + locationValue.getLocationType().toString());
                        locationAdapter.add("Latitude, Longitude: " +
                                Double.toString(locationValue.getLatitude()) + ", " + Double.toString(locationValue.getLongitude()));
                        locationAdapter.add("Address: " + locationValue.getAddress());
                        locationAdapter.add("Phone Number: " + locationValue.getPhoneNumber());
                        locationAdapter.add("Website: " + locationValue.getWebsiteLink());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d(TAG, "Failed to read value" + error.toException());
                    }
                });
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

    }
    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.backButton) {
            startActivity(new Intent(LocationInfoActivity.this, LocationListActivity.class));
            finish();
        } else if (i == R.id.lookAtInventoryButton) {
            Intent intent = new Intent(LocationInfoActivity.this, ItemListActivity.class);
            intent.putExtra("locationName", locationName);
            intent.putExtra("locationKey", locationKey);
            startActivity(intent);
            finish();
        }
    }
}
