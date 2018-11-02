package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.AccountType;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ItemListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ItemListActivity";

    private ListView itemList;
    private Button btnAdd;
    private Button btnBack;
    private String locationKey;
    private String locationName;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference itemRef;

    private String itemKey;
    private ArrayAdapter<Item> itemAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mAuth = FirebaseAuth.getInstance();

        itemList = findViewById(R.id.itemList);
        btnAdd = findViewById(R.id.addButton);
        btnBack = findViewById(R.id.backButton);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        itemList.setAdapter(itemAdapter);

        // get intent information
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                locationKey = null;
                locationName = null;
            } else {
                locationKey = extras.getString("locationKey");
                locationName = extras.getString("locationName");
            }
        } else {
            locationKey = (String) savedInstanceState.getSerializable("locationKey");
            locationName = (String) savedInstanceState.getSerializable("locationName");
        }


        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(ItemListActivity.this, ViewItemActivity.class);
                itemDetails.putExtra("itemKey", itemKey);
                itemDetails.putExtra("locationName", locationName);
                itemDetails.putExtra("locationKey", locationKey);

                startActivity(itemDetails);
                finish();
            }
        });
    }

    private void setItemKey(String key) {
        itemKey = key;
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user is " + mAuth.getCurrentUser());

        // check for location employee, render add button
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(ItemListActivity.this, HomeActivity.class));
        } else {
            String mUid = mAuth.getCurrentUser().getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users/" + mUid);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Account value = dataSnapshot.getValue(Account.class);

                    Log.d(TAG, "value is " + value);

                    if (value.getType().toString().equals("Location Employee")) {
                        btnAdd.setVisibility(View.VISIBLE);
                    } else {
                        btnAdd.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Log.d(TAG, "Failed to read value" + error.toException());
                }
            });
        }

        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("items");

        Log.d(TAG, "locationKey is " + locationKey);

        itemsRef.orderByChild("locationId").equalTo(locationKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                Log.d(TAG, "key is " + dataSnapshot.getKey());

                setItemKey(dataSnapshot.getKey());

                itemRef = FirebaseDatabase.getInstance().getReference("items/" + itemKey);
                itemRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "items/" + itemKey);
                        Item item = dataSnapshot.getValue(Item.class);
                        itemAdapter.add(item);
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d(TAG, "Failed to read value" + error.toException());
                    }
                });
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String string) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String string) {
            }
        });


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.addButton) {
            Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);

            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        } else if (i == R.id.backButton) {
            Intent intent = new Intent(ItemListActivity.this, LocationInfoActivity.class);

            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        }
    }


}
