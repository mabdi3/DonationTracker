package com.example.abdim.donationtracker.controllers;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;

import java.io.Serializable;
import java.util.List;

public class LocationListActivity extends AppCompatActivity {
    private ListView locationList;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location_list);
        locationList = (ListView) findViewById(R.id.location_list);
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, Locations.getLocations());
        locationList.setAdapter(locationAdapter);
        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent locationDetails = new Intent(LocationListActivity.this, LocationInfoActivity.class);
                Location location = Locations.getLocations().get(position);
                String name = location.getName();
                String locationType = location.getLocationType().toString();
                Double longitude = location.getLongitude();
                Double latitude = location.getLatitude();
                String address = location.getAddress();
                String phoneNumber = location.getPhoneNumber();
                String websiteLink = location.getWebsiteLink();
                locationDetails.putExtra("location", (Serializable) location);
                locationDetails.putExtra("name", name);
                locationDetails.putExtra("locationType", locationType);
                locationDetails.putExtra("longitude", longitude.toString());
                locationDetails.putExtra("latitude", latitude.toString());
                locationDetails.putExtra("address", address);
                locationDetails.putExtra("phoneNumber", phoneNumber);
                locationDetails.putExtra("websiteLink", websiteLink);
                startActivity(locationDetails);
            }
        });
        }
}
