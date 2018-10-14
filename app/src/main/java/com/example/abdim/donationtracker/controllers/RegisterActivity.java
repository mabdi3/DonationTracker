package com.example.abdim.donationtracker.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.RegisteredAccounts;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.AccountType;
// import android.widget.TextView;
// import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private Button back;
    private Button register;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Spinner accountType;
    private final int USERNAME_MINLENGTH = 4;

    /*
     * Check to see if passwords match, if username filled out to enable register button
     */
    private void enableRegister() {

        boolean isReady = username.getText().toString().length() >= USERNAME_MINLENGTH
                && password.getText().toString().length() > 0
                && confirmPassword.getText().toString().equals(password.getText().toString());
        register.setEnabled(isReady);
    };

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
        ArrayAdapter<AccountType> adapter = new ArrayAdapter<AccountType>(this,
                android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);

        enableRegister();

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if (!(username.getText().toString().length() >= USERNAME_MINLENGTH)) {
                    username.setError("Username must be " + USERNAME_MINLENGTH +
                            " characters or greater");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if (!(password.getText().toString().length() > 0)) {
                    password.setError("Password must have at least one character");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if (!(confirmPassword.getText().toString().equals(password.getText().toString()))) {
                    confirmPassword.setError("Passwords must match");
                }
                enableRegister();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // store it in RegisteredAccounts
                RegisteredAccounts rA = MainActivity.getRegisteredAccounts();
                Account account = new Account(username.getText().toString(),
                        password.getText().toString(),
                        (AccountType) accountType.getSelectedItem());
                if (password.getText().toString().equals(confirmPassword.getText().toString())
                        && !(rA.accountExists(account))) {


                    rA.addAccount(account);

                    AlertDialog alertDialog = new AlertDialog.Builder(
                            RegisterActivity.this).create();
                    alertDialog.setTitle("Registration Successful");
                    alertDialog.setMessage("User successfully added!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //send back to login page
                                    Intent intent = new Intent(RegisterActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                    alertDialog.show();

                }
                Log.d("Register",account.toString());
                RegisteredAccounts.printData();


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
