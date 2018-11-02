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
import com.example.abdim.donationtracker.models.ItemList;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ItemListActivity";

    private ListView itemlist;
    private Button btnAdd;
    private Button btnBack;

    private FirebaseAuth mAuth;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mAuth = FirebaseAuth.getInstance();

        itemlist = findViewById(R.id.itemList);
        btnAdd = findViewById(R.id.addButton);
        btnBack = findViewById(R.id.backButton);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();
        Locations.getLocationsAsList();
        final Location location = (Location) Locations.getLocationsAsList().get(intent.getExtras().getInt("location"));
        List<Item> itemArray = location.getLocationItemList().getItemList();

//        // for testing purposes, adds a random item in
//        itemArray.add(new Item("adidas ultraboost", "good shoes", 6, null, location, new ItemCategory("Clothing"), "Thursday, October 25, 2018 at 9:01 PM", 50.00) );

        ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemArray);
        itemlist.setAdapter(itemAdapter);

        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(ItemListActivity.this, ViewItemActivity.class);
                List<Item> itemsAsList = location.getLocationItemList().getItemList();
                Item item = itemsAsList.get(position);
                itemDetails.putExtra("location", location);
                itemDetails.putExtra("item", item);
                startActivity(itemDetails);
                finish();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user is " + mAuth.getCurrentUser());

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
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.addButton) {
            Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);

            intent.putExtra("location", getIntent().getExtras().getInt("location"));
            startActivity(intent);
            finish();
        } else if (i == R.id.backButton) {
            Intent intent = new Intent(ItemListActivity.this, LocationInfoActivity.class);
            intent.putExtra("location", getIntent().getExtras().getInt("location"));
            startActivity(intent);
            finish();
        }
    }


}
