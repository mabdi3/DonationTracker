package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.text.Editable;
import android.widget.TextView;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.ItemList;
import com.example.abdim.donationtracker.models.Location;
import com.example.abdim.donationtracker.models.Locations;

import android.text.TextWatcher;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {
    private EditText itemname;
    private EditText itemquantity;
    private EditText itemdesc;
    private EditText itemvalue;
    private TextView addcategory;
    private Spinner spinnercate;
    private Button addbutton;
    private Button backbutton;

    private boolean enableAdd() {
        boolean valid = false;

        int itemQuantityInt = itemquantity.getText().toString().equals("") ? 0 : Integer.parseInt(
                itemquantity.getText().toString());
        int itemValueInt = itemvalue.getText().toString().equals("") ? 0 : Integer.parseInt(
                itemvalue.getText().toString());
        if (itemname.getText().toString().length() >= 4
                && itemdesc.getText().toString().length() > 0
                && itemQuantityInt > 0
                && itemValueInt > 0) {
            valid = true;
        }
        return valid;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemname = findViewById(R.id.itemname);
        itemquantity = findViewById(R.id.itemquantity);
        itemdesc = findViewById(R.id.itemdesc);
        itemvalue = findViewById(R.id.itemvalue);
        addcategory = findViewById(R.id.itemaddcategory);
        spinnercate = findViewById(R.id.spinnercate);
        addbutton = findViewById(R.id.buttonAddItem);
        backbutton = findViewById(R.id.buttonBack);
        final Intent intents = getIntent();

        spinnercate.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemCategories.getItemCategoriesAsList()));

        final Account currentAccount = (Account) getIntent().getExtras().getSerializable("currentAccount");

        addbutton.setEnabled(enableAdd());

        itemvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                int itemValueInt = itemvalue.getText().toString().equals("") ? 0 : Integer.parseInt(
                        itemvalue.getText().toString());

                if (!(itemValueInt > 0)) {
                    itemvalue.setError("Passwords must match");
                }
                addbutton.setEnabled(enableAdd());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);

                Location currLocation = Locations.getLocationsAsList().get(intents.getExtras().getInt("location"));

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                String datetime = format.format(calendar.getTime());

                currLocation.addItem(new Item(itemname.getText().toString(),
                        itemdesc.getText().toString(),
                        Integer.parseInt(itemquantity.getText().toString()),
                        null,
                        currLocation,
                        new ItemCategory(spinnercate.getSelectedItem().toString()),
                        datetime,
                        Double.parseDouble(itemvalue.getText().toString())));
                intent.putExtra("location", intents.getExtras().getInt("location"));
                intent.putExtra("currentAccount", currentAccount);
                startActivity(intent);
                finish();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);
                intent.putExtra("location", getIntent().getExtras().getSerializable("location"));
                intent.putExtra("currentAccount", currentAccount);
                startActivity(intent);
                finish();
            }
        });



    }


}
