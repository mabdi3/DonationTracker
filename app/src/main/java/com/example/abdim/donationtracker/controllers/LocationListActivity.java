package com.example.abdim.donationtracker.controllers;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;

public class LocationListActivity extends AppCompatActivity {
    private ListView locationList;
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location_list);
        locationList = (ListView) findViewById(R.id.location_list);
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, Locations.getLocations());
        locationList.setAdapter(locationAdapter);
    }
}
