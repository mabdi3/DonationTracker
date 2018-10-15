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
                getIntent().putExtra("name", Locations.getLocations().get(position).getName());
                startActivity(locationDetails);
            }
        });
        }
}
