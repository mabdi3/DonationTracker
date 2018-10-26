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

public class HomeActivity extends AppCompatActivity {
    private Button login;
    private TextView welcome;
    private Button Register;
    private static boolean firstTime = true;
    private static final String TAG = "LocationListActivity";


    public static void setFirstTime(boolean first) {
        firstTime = first;
    }


    public static boolean getFirstTime() {
        return firstTime;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        login = findViewById(R.id.btnLogin);
        welcome = findViewById(R.id.textWelcome);
        welcome.setText("Welcome to Donation Tracker");
        Register = (Button)findViewById(R.id.registerBtn);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(login);
                // finish();
            }
        });
        if (firstTime) {
            readSDFile();
        }
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
                Locations.addLocation(newLocal);
            }
            br.close();
        } catch (IOException e) {
            Log.e(HomeActivity.TAG, "error reading assets", e);
        }
    }
}
