package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.abdim.donationtracker.models.Locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity {

    ListView list;
//    TextView name;
//    TextView description;
//    TextView quantity;
//    TextView location;
//    TextView category;
//    TextView time;
//    TextView value;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        list = findViewById(R.id.item_properties);
//        name = findViewById(R.id.item_name);
//        description = findViewById(R.id.Description);
//        quantity = findViewById(R.id.quantity);
//        location = findViewById(R.id.location);
//        category = findViewById(R.id.Category);
//        time = findViewById(R.id.time);
//        value = findViewById(R.id.item_value);
        backButton = findViewById(R.id.backButton);
        Intent intent = getIntent();

        Item receievedItem = (Item) intent.getExtras().getSerializable("item");
        final List<String> itemPropertiesList = new ArrayList<>(Arrays.asList(
                "Name: " + receievedItem.getName(),
                "Description:\n" + receievedItem.getDescription(),
                "Quantity: " + Integer.toString(receievedItem.getQuantity()),
                "Location: " + receievedItem.getLocation().toString(),
                "Category: " + receievedItem.getCategory().toString(),
                "Time of Donation: " + receievedItem.getTime(),
                "Value: " + String.format("$%.2f", receievedItem.getValue())
                ));
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemPropertiesList);
        list.setAdapter(itemAdapter);

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");


//        name.setText(receievedItem.getName());
//        description.setText(receievedItem.getDescription());
//        quantity.setText((new Integer(receievedItem.getQuantity())).toString());
//        location.setText(receievedItem.getLocation().toString());
//        category.setText(receievedItem.getCategory().toString());
//        time.setText(receievedItem.getTime());
//        value.setText(String.format("$%.2f", receievedItem.getValue()));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewItemActivity.this, ItemListActivity.class);
                Location location = (Location) getIntent().getExtras().getSerializable("location");
                intent.putExtra("location", location);
                intent.putExtra("currentAccount", currentAccount);
                startActivity(intent);
                finish();
            }
        });

    }
}
