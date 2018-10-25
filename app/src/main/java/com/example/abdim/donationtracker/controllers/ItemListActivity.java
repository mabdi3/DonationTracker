package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.ItemList;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private ListView itemlist;
    private Button addButton;
    private Button backButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        itemlist = findViewById(R.id.itemList);
        Intent intent = getIntent();
        final Location location = (Location) intent.getExtras().getSerializable("location");
        List<Item> itemArray = location.getLocationItemList().getItemList();

        //TODO for testing purposes, adds a random item in
        itemArray.add(new Item("adidas ultraboost", "good shoes", 6, null, location, new ItemCategory("Clothing"), "Thursday, October 25, 2018 at 9:01 PM", 50.00) );

        ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemArray);
        itemlist.setAdapter(itemAdapter);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");


        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);

                intent.putExtra("location", getIntent().getExtras().getSerializable("location"));
                intent.putExtra("currentAccount", currentAccount);

                startActivity(intent);
                finish();
            }
        });

        itemlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(ItemListActivity.this, ViewItemActivity.class);
                List<Item> itemsAsList = location.getLocationItemList().getItemList();
                Item item = itemsAsList.get(position);
                itemDetails.putExtra("location", location);
                itemDetails.putExtra("item", item);
                itemDetails.putExtra("currentAccount", currentAccount);
                startActivity(itemDetails);
                finish();
            }
        });

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemListActivity.this, LocationInfoActivity.class);
                intent.putExtra("location", getIntent().getExtras().getSerializable("location"));
                intent.putExtra("currentAccount", currentAccount);
                startActivity(intent);
                finish();
            }
        });

    }


}
