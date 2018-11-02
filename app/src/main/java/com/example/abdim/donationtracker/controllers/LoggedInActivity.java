package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.util.Log;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.AccountType;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class LoggedInActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "LocationListActivity";

    private Button btnToList;
    private Button btnLogout;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        btnToList = findViewById(R.id.btnToList);
        btnLogout = findViewById(R.id.btnLogout);

        btnToList.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user is " + mAuth.getCurrentUser());

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(LoggedInActivity.this, HomeActivity.class));
        } else {
            String mUid = mAuth.getCurrentUser().getUid();

            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users/" + mUid);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "yeet" + AccountType.valueOf("Location_Employee"));
                    Account value = dataSnapshot.getValue(Account.class);

                    Log.d(TAG, "value is " + value);
                    Log.d(TAG, "type is" + value.getType());
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Log.d(TAG, "Failed to read value" + error.toException());
                }


            });

        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.btnToList) {
            startActivity(new Intent(LoggedInActivity.this, LocationListActivity.class));
        } else if (i == R.id.btnLogout) {
            startActivity(new Intent(LoggedInActivity.this, HomeActivity.class));
            mAuth.signOut();
        }
        finish();
    }
}