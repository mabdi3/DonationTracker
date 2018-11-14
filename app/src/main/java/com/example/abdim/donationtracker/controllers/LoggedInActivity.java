package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Activity for logged in user
 */
public class LoggedInActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "LocationListActivity";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Button btnToList = findViewById(R.id.btnToList);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnToLocationMap = findViewById(R.id.btnToLocationMap);

        btnToList.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnToLocationMap.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user is " + mAuth.getCurrentUser());

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(LoggedInActivity.this, HomeActivity.class));
        } else {
            String mUid = mAuth.getCurrentUser().getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().
                    getReference("users/" + mUid);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Account value = dataSnapshot.getValue(Account.class);

                    Log.d(TAG, "value is " + value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG, "Failed to read value" + error.toException());
                }


            });

        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch(i) {
            case R.id.btnToList : {
                startActivity(new Intent(LoggedInActivity.this, LocationListActivity.class));
                finish();
            }
            break;
            case R.id.btnToLocationMap : {
                startActivity(new Intent(LoggedInActivity.this, MapsActivity.class));
                finish();
            }
            break;
            case R.id.btnLogout : {
                startActivity(new Intent(LoggedInActivity.this, HomeActivity.class));
                mAuth.signOut();
                finish();
            }
            break;
            default : {
            }
        }
    }
}