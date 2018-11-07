package com.example.abdim.donationtracker.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

public class LocationListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LocationListActivity";

    private ListView locationListView;
    private Button btnBack;
    private Button btnSearchAll;

    private ArrayAdapter<Location> locationAdapter;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location_list);

        locationListView = findViewById(R.id.location_list);
        btnBack = findViewById(R.id.backButton);
        btnSearchAll = findViewById(R.id.searchAllButton);


        btnBack.setOnClickListener(this);
        btnSearchAll.setOnClickListener(this);

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        locationListView.setAdapter(locationAdapter);


        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent locationInfoIntent = new Intent(
                        LocationListActivity.this, LocationInfoActivity.class);

                String locationName = locationListView.getItemAtPosition(position).toString();
                Log.d(TAG, locationName);

                locationInfoIntent.putExtra("locationName", locationName);
                startActivity(locationInfoIntent);
                finish();
            }
        });

        setLocations();
    }

    private void setLocations() {
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
        } else if (i == R.id.searchAllButton) {
            Intent intent = new Intent(LocationListActivity.this, ItemListActivity.class);

            intent.putExtra("searchAllLocations", true);

            startActivity(intent);
            finish();
        }
    }
}
