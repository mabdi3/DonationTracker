package com.example.abdim.donationtracker;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        username = (EditText)findViewById(R.id.new_username);
        password = (EditText)findViewById(R.id.new_password);
        confirmPassword = (EditText)findViewById(R.id.confirm_password);
        back = (Button)findViewById(R.id.back_button);
        register = (Button)findViewById(R.id.register_button);
        accountType = (Spinner) findViewById(R.id.account_type_spinner);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //TODO 1 have the register button create an account object with the info provided;
            // store it in RegisteredAccounts
                RegisteredAccounts rA = MainActivity.getRegisteredAccounts();
                Account account = new Account(username.getText().toString(),
                    password.getText().toString(),
                    AccountType.valueOf(accountType.getSelectedItem().toString()));
                if (password.equals(confirmPassword) && !(rA.accountExists(account))) {
                    rA.addAccount(account);

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 2 make it go back
            }
        });
    }
}
