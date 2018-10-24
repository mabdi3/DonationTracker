package com.example.abdim.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.example.abdim.donationtracker.models.ItemList;
import com.example.abdim.donationtracker.models.Location;

public class AddItemActivity extends AppCompatActivity {
    private EditText itemname;
    private EditText itemquantity;
    private EditText itemtime;
    private EditText itemdate;
    private EditText itemdesc;
    private EditText itemvalue;
    private TextView addcategory;
    private Spinner spinnercate;
    private Button addbutton;
    private Button backbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemname = findViewById(R.id.itemname);
        itemquantity = findViewById(R.id.itemquantity);
        itemtime = findViewById(R.id.itemtime);
        itemdate = findViewById(R.id.itemdate);
        itemdesc = findViewById(R.id.itemdesc);
        itemvalue = findViewById(R.id.itemvalue);
        addcategory = findViewById(R.id.itemaddcategory);
        spinnercate = findViewById(R.id.spinnercate);
        addbutton = findViewById(R.id.buttonAddItem);
        backbutton = findViewById(R.id.buttonBack);
        Intent intent = getIntent();

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);

                ItemList.addItem(new Item(itemname.getText().toString(),
                        itemdesc.getText().toString(),
                        Integer.parseInt(itemquantity.getText().toString()),
                        null,
                        (Location) intent.getExtras().getSerializable("location"),
                        new ItemCategory(addcategory.getText().toString()),
                        itemdate.getText().toString() + ", " + itemtime.getText().toString(),
                        Double.parseDouble(itemvalue.getText().toString())));

                startActivity(intent);
                finish();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this, ItemListActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


}
