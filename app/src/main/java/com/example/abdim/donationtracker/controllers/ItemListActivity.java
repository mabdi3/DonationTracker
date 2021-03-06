package com.example.abdim.donationtracker.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.AccountType;
import com.example.abdim.donationtracker.models.Item;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * ItemListActivity class
 */
public class ItemListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ItemListActivity";

    private Button btnAdd;

    private EditText editSearch;

    @Nullable
    private String locationKey;
    @Nullable
    private String locationName;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference itemRef;

    private String itemKey;
    private ArrayAdapter<Item> itemAdapter;

    private final Collection<Item> itemArray = new ArrayList<>();

    private List<String> itemKeyList = new ArrayList<>();

    boolean resultsFound;

    @Nullable
    private boolean searchAllLocations;
    // private ArrayList<Item> tempArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mAuth = FirebaseAuth.getInstance();

        ListView itemList = findViewById(R.id.itemList);
        btnAdd = findViewById(R.id.addButton);
        Button btnBack = findViewById(R.id.backButton);

        editSearch = findViewById(R.id.editSearch);

        Button btnSearchCategory = findViewById(R.id.btnSearchCategory);
        Button btnSearchName = findViewById(R.id.btnSearchName);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        btnSearchCategory.setOnClickListener(this);
        btnSearchName.setOnClickListener(this);

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        itemList.setAdapter(itemAdapter);

        // get intent information
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            if (extras == null) {
                locationKey = null;
                locationName = null;
                searchAllLocations = false;
            } else {
                locationKey = extras.getString("locationKey");
                locationName = extras.getString("locationName");
                searchAllLocations = extras.getBoolean("searchAllLocations");
            }
        } else {
            locationKey = (String) savedInstanceState.getSerializable("locationKey");
            locationName = (String) savedInstanceState.getSerializable("locationName");
            searchAllLocations = (savedInstanceState.getSerializable("searchAllLocations") ==
                    null) ? (Boolean) savedInstanceState.getSerializable("searchAllLocations")
                    : null;
        }


        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(ItemListActivity.this, ViewItemActivity.class);


                String itemKeyFinal = itemKeyList.get(position);

                itemDetails.putExtra("itemKey", itemKeyFinal);
                itemDetails.putExtra("locationName", locationName);
                itemDetails.putExtra("locationKey", locationKey);

                if (searchAllLocations) {

                    itemDetails.putExtra("backSearchAllLocations", true);
                }
                startActivity(itemDetails);
                finish();
            }
        });

        setItemList();
    }

    private void setItemKey(String key) {
        itemKey = key;
        itemKeyList.add(key);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user is " + mAuth.getCurrentUser());

        // check for location employee, render add button
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(ItemListActivity.this, HomeActivity.class));
        } else {
            FirebaseUser mUser = mAuth.getCurrentUser();
            String mUid = mUser.getUid();

            FirebaseDatabase mInstance = FirebaseDatabase.getInstance();
            DatabaseReference userRef = mInstance.getReference("users/" + mUid);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Account value = dataSnapshot.getValue(Account.class);

                    Log.d(TAG, "value is " + value);

                    AccountType valueType = Objects.requireNonNull(value).getType();

                    String valueTypeString = valueType.toString();
                    if ("Location Employee".equals(valueTypeString) && !searchAllLocations) {
                        btnAdd.setVisibility(View.VISIBLE);
                    } else {
                        btnAdd.setVisibility(View.INVISIBLE);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d(TAG, "Failed to read value" + error.toException());
                }
            });
        }
    }

    private void searchItems(String searchParam) {

        String query = editSearch.getText().toString();
        Collection<Item> newList = new ArrayList<>();
        if (searchParam.equals("name")) {
            if (query.equals("")) {
                itemAdapter.clear();
                itemAdapter.addAll(itemArray);
            } else {
                itemAdapter.clear();
                List<String> newItemKeyList = new ArrayList<>();
                for (Item i : itemArray) {
                    if (i.toString().equals(query)) {
                        newList.add(i);
                        newItemKeyList.add(i.getId());
                    }
                }
                itemKeyList = newItemKeyList;
                if (newList.isEmpty()) {
                    Toast.makeText(ItemListActivity.this, "Nothing matched query",
                            Toast.LENGTH_SHORT).show();
                }
                itemAdapter.addAll(newList);
            }
        } else {
            if (query.equals("")) {
                itemAdapter.clear();
                itemAdapter.addAll(itemArray);
            } else {
                itemAdapter.clear();
                List<String> newItemKeyList = new ArrayList<>();
                for (Item i : itemArray) {
                    if (i.getCategory().toString().toLowerCase().equals(query)) {
                        newList.add(i);
                        newItemKeyList.add(i.getId());
                    }
                }
                itemKeyList = newItemKeyList;
                if (newList.isEmpty()) {
                    Toast.makeText(ItemListActivity.this, "Nothing matched query",
                            Toast.LENGTH_SHORT).show();
                }
                itemAdapter.addAll(newList);
            }
        }
    }
    private void setItemList() {
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("items");

        Log.d(TAG, "locationKey is " + locationKey);
        Log.d(TAG, "searchAllLocations is " + searchAllLocations);
        if (!searchAllLocations) {
            itemsRef.orderByChild("locationId").equalTo(locationKey).addChildEventListener(
                    new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {

                    Log.d(TAG, "key is " + dataSnapshot.getKey());

                    setItemKey(dataSnapshot.getKey());

                    itemRef = FirebaseDatabase.getInstance().getReference("items/" + itemKey);
                    itemRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d(TAG, "items/" + itemKey);
                            Item item = dataSnapshot.getValue(Item.class);
                            itemAdapter.add(item);
                            itemArray.add(item);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d(TAG, "Failed to read value" + error.toException());
                        }
                    });
                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String string) {
                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String string) {
                }
            });
        } else {
            itemsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot itemShot : dataSnapshot.getChildren()) {

                        setItemKey(itemShot.getKey());

                        Item itemValue = itemShot.getValue(Item.class);
                        itemAdapter.add(itemValue);
                        itemArray.add(itemValue);
                        Log.d(TAG, "here is item name "
                                + Objects.requireNonNull(itemValue).getName());

                    }
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

        if (i == R.id.addButton) {
            Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);

            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        } else if ((i == R.id.backButton) && !searchAllLocations) {
            Intent intent = new Intent(ItemListActivity.this, LocationInfoActivity.class);

            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        } else if (i == R.id.btnSearchCategory) {
            this.searchItems("category");
        } else if (i == R.id.btnSearchName) {
            this.searchItems("name");
        } else if (i == R.id.backButton) {
            startActivity(new Intent(ItemListActivity.this, LocationListActivity.class));
            finish();
        }
    }


}
