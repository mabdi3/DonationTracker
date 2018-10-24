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
import com.example.abdim.donationtracker.models.Item;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        itemlist = findViewById(R.id.itemList);
        Intent intent = getIntent();
        final Location location = (Location) intent.getExtras().getSerializable("location");
        List<Item> itemArray = location.getLocationItemList().getItemList();

        //TODO for testing purposes
        itemArray.add(new Item("hi", "hi", 20, null, location, new ItemCategory("cate"), "tim", 20.00 ));

        ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itemArray);
        itemlist.setAdapter(itemAdapter);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemListActivity.this, AddItemActivity.class);

                Location location = (Location) getIntent().getExtras().getSerializable("location");
                intent.putExtra("location", location);

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
                startActivity(itemDetails);
                finish();
            }
        });



    }


}
