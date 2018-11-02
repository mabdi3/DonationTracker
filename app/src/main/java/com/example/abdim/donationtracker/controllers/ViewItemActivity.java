package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.Location;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "ViewItemActivity";

    private ListView list;
//    TextView name;
//    TextView description;
//    TextView quantity;
//    TextView location;
//    TextView category;
//    TextView time;
//    TextView value;
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

        /*
        Item receievedItem = (Item) intents.getExtras().getSerializable("item");
        final List<String> itemPropertiesList = new ArrayList<>(Arrays.asList(
                "Name: " + receievedItem.getName(),
                "Description:\n" + receievedItem.getDescription(),
                "Quantity: " + Integer.toString(receievedItem.getQuantity()),
                // "Location: " + receievedItem.getLocation().toString(),
                "Category: " + receievedItem.getCategory().toString(),
                "Time of Donation: " + receievedItem.getTime(),
                "Value: " + String.format("$%.2f", receievedItem.getValue())
                ));
        */
        itemDetailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(itemDetailAdapter);


//        name.setText(receievedItem.getName());
//        description.setText(receievedItem.getDescription());
//        quantity.setText((new Integer(receievedItem.getQuantity())).toString());
//        location.setText(receievedItem.getLocation().toString());
//        category.setText(receievedItem.getCategory().toString());
//        time.setText(receievedItem.getTime());
//        value.setText(String.format("$%.2f", receievedItem.getValue()));

    }
    @Override
    public void onStart() {
        super.onStart();

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
