package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
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

public class ViewItemActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "ViewItemActivity";

    private ListView list;
    private Button btnBack;
    private String locationKey;
    private String locationName;
    private String itemKey;

    private DatabaseReference itemRef;

    private ArrayAdapter<String> itemDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        list = findViewById(R.id.item_properties);
        btnBack = findViewById(R.id.backButton);

        btnBack.setOnClickListener(this);

        // get intent information
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                locationKey = null;
                locationName = null;
                itemKey = null;
            } else {
                locationKey = extras.getString("locationKey");
                locationName = extras.getString("locationName");
                itemKey = extras.getString("itemKey");
            }
        } else {
            locationKey = (String) savedInstanceState.getSerializable("locationKey");
            locationName = (String) savedInstanceState.getSerializable("locationName");
            itemKey = (String) savedInstanceState.getSerializable("itemKey");
        }
        itemDetailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(itemDetailAdapter);

        setItemInformation();

    }

    private void setItemInformation() {
        itemRef = FirebaseDatabase.getInstance().getReference("items/" + itemKey);
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "items/" + itemKey);
                Item item = dataSnapshot.getValue(Item.class);
                itemDetailAdapter.add("Name: " + item.getName());
                itemDetailAdapter.add("Description:\n" + item.getDescription());
                itemDetailAdapter.add("Quantity: " + Integer.toString(item.getQuantity()));
                itemDetailAdapter.add("Category: " + item.getCategory().toString());
                itemDetailAdapter.add("Time of Donation: " + item.getTime());
                itemDetailAdapter.add("Value: " + String.format("$%.2f", item.getValue()));
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value" + error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.backButton) {
            Intent intent = new Intent(ViewItemActivity.this, ItemListActivity.class);

            intent.putExtra("locationName", locationName);
            intent.putExtra("locationKey", locationKey);

            startActivity(intent);
            finish();
        }
    }
}
