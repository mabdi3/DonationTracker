package com.example.abdim.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private Button back;
    private Button register;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Spinner accountType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.new_username);
        password = (EditText) findViewById(R.id.new_password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        back = (Button) findViewById(R.id.back_button);
        register = (Button) findViewById(R.id.register_button);
        accountType = (Spinner) findViewById(R.id.account_type_spinner);

        /*
          Set up the adapter to display the allowable AccountTypes in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // store it in RegisteredAccounts
                RegisteredAccounts rA = MainActivity.getRegisteredAccounts();
                Account account = new Account(username.getText().toString(),
                        password.getText().toString(),
                        (AccountType) accountType.getSelectedItem());
                if (password.getText().toString().equals(confirmPassword.getText().toString()) && !(rA.accountExists(account))) {
                    rA.addAccount(account);
                }
                Log.d("Register",account.toString());
                RegisteredAccounts.printData();

                //send back to login page
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();hi
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
