package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.text.Editable;
import android.widget.TextView;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategories;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.text.TextWatcher;

import java.util.Map;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("ALL")
public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddItemActivity";

    private EditText itemname;
    private EditText itemquantity;
    private EditText itemdesc;
    private EditText itemvalue;
    private Spinner spinnercate;

    private Button btnAdd;

    private String locationKey;
    private String locationName;

    private Item newItem;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemname = findViewById(R.id.itemname);
        itemquantity = findViewById(R.id.itemquantity);
        itemdesc = findViewById(R.id.itemdesc);
        itemvalue = findViewById(R.id.itemvalue);
        TextView addcategory = findViewById(R.id.itemaddcategory);
        spinnercate = findViewById(R.id.spinnercate);

        btnAdd = findViewById(R.id.buttonAddItem);
        Button btnBack = findViewById(R.id.buttonBack);

        btnAdd.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        btnAdd.setEnabled(enableAdd());

        mDatabase = FirebaseDatabase.getInstance().getReference();

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

        spinnercate.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ItemCategories.getItemCategoriesAsList()));

        itemvalue.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

                double itemValueDouble = itemvalue.getText().toString().equals("") ? 0 : Double.parseDouble(
                        itemvalue.getText().toString());

                if (!(itemValueDouble >= 0)) {
                    itemvalue.setError("Passwords must match");
                }
                btnAdd.setEnabled(enableAdd());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }

    private void writeNewItem(Item newItem) {
        Map<String, Object>  itemValues = newItem.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/items/" + newItem.getId(), itemValues);

        mDatabase.updateChildren(childUpdates);


    }
    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.buttonAddItem) {
            Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);
            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            String dateTime = format.format(calendar.getTime());

            DatabaseReference pushedItemRef = FirebaseDatabase.getInstance().getReference("items").push();
            String itemId = pushedItemRef.getKey();

            newItem = new Item(
                    itemId,
                    itemname.getText().toString(),
                    itemdesc.getText().toString(),
                    Integer.parseInt(itemquantity.getText().toString()),
                    locationKey,
                    new ItemCategory(spinnercate.getSelectedItem().toString()),
                    dateTime,
                    Double.parseDouble(itemvalue.getText().toString())
            );
            /*
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future future = executorService.submit(new Runnable() {
                public void run() {
                    createLocation(AddItemActivity.this.newItem);
                }
            });
            Object object = null;
            try {
                object = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if (object != null) {

                Log.d(TAG, "heyo3.0 " + newItem.getLocationName());
                writeNewItem(newItem);
            }
            */
            writeNewItem(newItem);
            startActivity(intent);
            finish();
        } else if (i == R.id.buttonBack) {
            Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);
            intent.putExtra("locationKey", locationKey);
            intent.putExtra("locationName", locationName);
            startActivity(intent);
            finish();
        }
    }
    private void createLocation(Item item) {
        item.createLocationName(item.getLocationId());
        Log.d(TAG, "HEEEEY");
    }
    private boolean enableAdd() {
        boolean valid = false;

        int itemQuantityInt = itemvalue.getText().toString().equals("") ? 0 : Integer.parseInt(
                itemquantity.getText().toString());
        double itemValueDouble = itemvalue.getText().toString().equals("") ? 0 : Double.parseDouble(
                itemvalue.getText().toString());
        if ((itemname.getText().toString().length() >= 4)
                && !itemdesc.getText().toString().isEmpty()
                && (itemQuantityInt > 0)
                && (itemValueDouble >= 0)) {
            valid = true;
        }
        return valid;
    }



}
