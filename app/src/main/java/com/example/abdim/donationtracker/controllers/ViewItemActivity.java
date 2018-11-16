package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

// @SuppressWarnings("ALL")

/**
 * ViewItemActivity
 */
public class ViewItemActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewItemActivity";

    @Nullable
    private String locationKey;
    @Nullable
    private String locationName;
    @Nullable
    private String itemKey;

    private boolean backSearchAllLocations;

    private ArrayAdapter<String> itemDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        ListView list = findViewById(R.id.item_properties);
        Button btnBack = findViewById(R.id.backButton);

        btnBack.setOnClickListener(this);

        // get intent information
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                locationKey = null;
                locationName = null;
                itemKey = null;

                backSearchAllLocations = false;
            } else {
                locationKey = extras.getString("locationKey");
                locationName = extras.getString("locationName");
                itemKey = extras.getString("itemKey");

                backSearchAllLocations = extras.getBoolean("backSearchAllLocations");
            }
        } else {
            locationKey = (String) savedInstanceState.getSerializable("locationKey");
            locationName = (String) savedInstanceState.getSerializable("locationName");
            itemKey = (String) savedInstanceState.getSerializable("itemKey");

            backSearchAllLocations = (Boolean) savedInstanceState.getSerializable(
                    "backSearchAllLocations");
        }
        itemDetailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(itemDetailAdapter);

        this.setItemInformation();

    }
    public static boolean checkIfValidForDisplay(Item i) {
        if(i.getCategory() == null) {
            return false;
        } else if(i.getName() == null) {
            return false;
        } else if (i.getDescription() == null) {
            return false;
        } else if (i.getId() == null) {
            return false;
        } else if (i.getTime() == null) {
            return false;
        } else {
            return true;
        }
    }

    private void setItemInformation() {
        DatabaseReference itemRef = FirebaseDatabase.getInstance().getReference("items/" + itemKey);
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "items/" + itemKey);
                Item item = dataSnapshot.getValue(Item.class);
                if(checkIfValidForDisplay(item)) {
                    itemDetailAdapter.add("Name: " + Objects.requireNonNull(item).getName());
                    itemDetailAdapter.add("Description:\n" + item.getDescription());
                    itemDetailAdapter.add("Location Id: " + item.getLocationId());
                    itemDetailAdapter.add("Quantity: " + Integer.toString(item.getQuantity()));
                    itemDetailAdapter.add("Category: " + item.getCategory().toString());
                    itemDetailAdapter.add("Time of Donation: " + item.getTime());
                    itemDetailAdapter.add("Value: " + String.format("$%.2f", item.getValue()));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Failed to read value" + error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        Log.d(TAG, "backSearchAll" + backSearchAllLocations);
        if ((i == R.id.backButton) && backSearchAllLocations) {

            Intent intent = new Intent(ViewItemActivity.this, LocationListActivity.class);
            startActivity(intent);
            finish();
        } else if (i == R.id.backButton) {


            Intent intent = new Intent(ViewItemActivity.this, ItemListActivity.class);

            intent.putExtra("locationName", locationName);
            intent.putExtra("locationKey", locationKey);

            startActivity(intent);
            finish();
        }
    }
}
