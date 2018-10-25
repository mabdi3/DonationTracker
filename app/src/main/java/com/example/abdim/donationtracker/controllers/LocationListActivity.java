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
        final List<Location> locationsAsList = new ArrayList<>(Locations.locations);
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, locationsAsList);
        locationList.setAdapter(locationAdapter);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");


        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LocationListActivity.this, LocationInfoActivity.class);

                Location location = locationsAsList.get(position);

                intent.putExtra("location", location);
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

        loadLocationFromCSVButton = findViewById(R.id.loadLocationFromCSVButton);
        loadLocationFromCSVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readSDFile();
                finish();
                startActivity(getIntent());
            }
        });

    }

    /**
     * Opens the LocationData.csv file in the /res/raw directory
     *
     * Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
     * Line Entry format:
     *  [0] = key
     *  [1] = name
     *  [2] = latitude
     *  [3] = longitude
     *  [4] = address
     *  [5] = city
     *  [6] = state
     *  [7] = zip
     *  [8] = type
     *  [9] = phone
     *  [10] = website
     */
    private void readSDFile() {
        try {
            //open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //from here we probably should call a model method and pass the inputstream
            //wrap it in a BufferedReader so that we get the readLine() method

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine();
            while((line = br.readLine()) != null) {
                Log.d(LocationListActivity.TAG, line);
                String[] tokens = line.split(",");
                Location newLocal = new Location(Integer.parseInt(tokens[0]),
                        tokens[1], LocationType.DROPOFFONLY,
                        Double.parseDouble(tokens[3]),
                        Double.parseDouble(tokens[2]),
                        tokens[4] + ", " +
                                tokens[5] + ", " +
                                tokens[6] + " " +
                                tokens[7],
                        tokens[9],
                        tokens[10]);
                if (tokens[8].equals("Store")) {
                    newLocal.setLocationType(LocationType.STORE);
                } else if (tokens[8].equals("Warehouse")) {
                    newLocal.setLocationType(LocationType.WAREHOUSE);
                }
                Locations.locations.add(newLocal);
            }
            br.close();
        } catch (IOException e) {
            Log.e(LocationListActivity.TAG, "error reading assets", e);
        }
    }

}
