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

public class LocationListActivity extends AppCompatActivity {
    private static final String TAG = "LocationListActivity";
    private ListView locationList;

    private Button backButton;

    private Button loadLocationFromCSVButton;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_location_list);
        locationList = (ListView) findViewById(R.id.location_list);
        final List<Location> locationsAsList = Locations.getLocationsAsList();
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locationsAsList);
        locationList.setAdapter(locationAdapter);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");


        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocationListActivity.this, LocationInfoActivity.class);

                Location location = locationsAsList.get(position);



                intent.putExtra("location", position);
                intent.putExtra("currentAccount", currentAccount);

                startActivity(intent);
                finish();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goback = new Intent(LocationListActivity.this, LoggedInActivity.class);
                goback.putExtra("currentAccount", currentAccount);
                startActivity(goback);
                finish();
            }
        });

//        loadLocationFromCSVButton = findViewById(R.id.loadLocationFromCSVButton);
//        loadLocationFromCSVButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                readSDFile();
//                finish();
//                startActivity(getIntent());
//            }
//        });

    }



}
