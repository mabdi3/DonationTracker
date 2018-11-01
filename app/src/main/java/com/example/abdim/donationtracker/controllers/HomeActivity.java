package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.LocationType;
import com.example.abdim.donationtracker.models.Locations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";

    private TextView welcome;

    private Button btnLogin;
    private Button btnReadCSV;
    private Button btnRegister;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        welcome = findViewById(R.id.textWelcome);
        welcome.setText("Welcome to Donation Tracker");


        btnLogin = findViewById(R.id.btnLogin);
        btnReadCSV = findViewById(R.id.btnReadCSV);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnReadCSV.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        super.onStart();

        int i = v.getId();

        if (i == R.id.btnLogin) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        } else if (i == R.id.btnRegister) {
            startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
            finish();
        } else if (i == R.id.btnReadCSV) {
            readSDFile();
        }
    }

    private void writeNewLocation(Location newLocation) {
        // String key = mDatabase.child("locations").push().getKey();
        Map<String, Object> locationValues = newLocation.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/locations/" + newLocation.getLocationId(), locationValues);

        mDatabase.updateChildren(childUpdates);
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
                Log.d(HomeActivity.TAG, line);
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

                writeNewLocation(newLocal);
                // Locations.addLocation(newLocal);
            }
            br.close();
        } catch (IOException e) {
            Log.e(HomeActivity.TAG, "error reading assets", e);
        }
    }
}
