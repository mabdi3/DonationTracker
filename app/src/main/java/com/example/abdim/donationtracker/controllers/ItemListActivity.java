package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.abdim.donationtracker.models.LocationType;
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
    private EditText itemSearch;
    private Button btnAdd;
    private Button btnBack;
    private Button searchNameButton;
    private Button searchTypeButton;
    private FirebaseAuth mAuth;
    private ArrayAdapter<Item> itemAdapter;
    List<Item> itemArray;
    List<Item> tempArray;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        mAuth = FirebaseAuth.getInstance();

        itemlist = findViewById(R.id.itemList);
        btnAdd = findViewById(R.id.addButton);
        btnBack = findViewById(R.id.backButton);
        itemSearch = findViewById(R.id.searchEditText);
        searchNameButton = findViewById(R.id.searchNameButton);
        searchTypeButton = findViewById(R.id.searchCategoryButton);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        searchNameButton.setOnClickListener(this);
        searchTypeButton.setOnClickListener(this);

        Intent intent = getIntent();
        //Locations.getLocationsAsList();
        final Location location = new Location(1, "1", LocationType.DROPOFFONLY, 1, 1, "1","1","1");
        itemArray = new ArrayList<Item>();
        itemArray.add(new Item("Apple", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(0), "1",1.0));
        itemArray.add(new Item("Banana", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(1), "1",1.0));
        itemArray.add(new Item("Grape", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(2), "1",1.0));
        itemArray.add(new Item("Strawberry", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(3), "1",1.0));
        itemArray.add(new Item("Orange", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(4), "1",1.0));
        itemArray.add(new Item("Blackberry", "Apple", 2, null, location, ItemCategories.getItemCategoriesAsList().get(0), "1",1.0));
        tempArray = new ArrayList<Item>();
        tempArray.addAll(itemArray);
//        // for testing purposes, adds a random item in
//        itemArray.add(new Item("adidas ultraboost", "good shoes", 6, null, location, new ItemCategory("Clothing"), "Thursday, October 25, 2018 at 9:01 PM", 50.00) );

        itemAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, tempArray);
        itemlist.setAdapter(itemAdapter);

        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(ItemListActivity.this, ViewItemActivity.class);
                List<Item> itemsAsList = tempArray;
                Item item = itemsAsList.get(position);
                itemDetails.putExtra("location", location);
                itemDetails.putExtra("item", item);
                startActivity(itemDetails);
                finish();
            }
        });

//        itemSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                ItemListActivity.this.itemAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        searchNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameToSearch = itemSearch.getText().toString();
                Log.d(TAG, "itemToSearchFor = " + itemNameToSearch);
                if(itemNameToSearch.equals("")) {
                    itemAdapter.clear();
                    itemAdapter.addAll(itemArray);
                } else {
                    List<Item> newList = new ArrayList<Item>();
                    itemAdapter.clear();
                    for (Item i : itemArray) {
                        if (i.getName().toString().equals(itemNameToSearch)) {
                            newList.add(i);
                        }
                    }
                    itemAdapter.addAll(newList);
                }
            }
        });
        searchTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameToSearch = itemSearch.getText().toString();
                Log.d(TAG, "itemToSearchFor = " + itemNameToSearch);
                if(itemNameToSearch.equals("")) {
                    itemAdapter.clear();
                    itemAdapter.addAll(itemArray);
                } else {
                    List<Item> newList = new ArrayList<Item>();
                    itemAdapter.clear();
                    for (Item i : itemArray) {
                        if (i.getCategory().toString().equals(itemNameToSearch)) {
                            newList.add(i);
                        }
                    }
                    itemAdapter.addAll(newList);
                }
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
