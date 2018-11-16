package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;

/**
 * Activity for home page
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    // private static final String TAG = "HomeActivity";

//    private FirebaseDatabase mDatabaseInstance;
//
//    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        TextView welcome = findViewById(R.id.textWelcome);
        welcome.setText("Welcome to Donation Tracker");


        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
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
        }
        /*
        else if (i == R.id.btnReadCSV) {
            readSDFile();
        }
        */
    }

//    private void writeNewLocation(Location newLocation) {
//        Map<String, Object> locationValues = newLocation.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/locations/" + newLocation.getLocationId(), locationValues);
//
//        mDatabase.updateChildren(childUpdates);
//    }

}
