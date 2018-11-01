package com.example.abdim.donationtracker.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.abdim.donationtracker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private int loginAttemptsRemaining = 5;
    private EditText emailField;
    private EditText passField;
    private TextView loginInfo;

    private Button btnCancel;
    private Button btnRegister;
    private Button btnSubmit;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = (EditText) findViewById(R.id.etUser);
        passField = (EditText) findViewById(R.id.etPassword);
        loginInfo = (TextView) findViewById(R.id.textLoginInfo);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnCancel.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        loginInfo.setText("Login Attempts Remaining: 5");

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user: " + mAuth.getCurrentUser());

        if (mAuth.getCurrentUser() != null) {
            // onAuthSuccess(mAuth.getCurrentUser());
            Log.d(TAG, "signing out");
            mAuth.signOut();
        }
    }

    private void signIn() {
        Log.d(TAG, "sign in");

        if (!validateForm()) {
            return;
        }

        String email = emailField.getText().toString();
        String password = passField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onComplete signIn " + task.isSuccessful());

                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, LoggedInActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Sign in Failed",
                                    Toast.LENGTH_SHORT).show();
                            loginAttemptsRemaining--;
                            loginInfo.setText("Login Attempts Remaining: " + String.valueOf(loginAttemptsRemaining));
                            if (loginAttemptsRemaining == 0) {
                                btnSubmit.setEnabled(false);
                            }
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(emailField.getText().toString())) {
            emailField.setError("Required");
            valid = false;
        } else {
            emailField.setError(null);
        }

        if (TextUtils.isEmpty(passField.getText().toString())) {
            passField.setError("Required");
            valid = false;
        } else {
            passField.setError(null);
        }
        return valid;
    }

    @Override
    public void onClick(View v) {

        Log.d(TAG, "getting here");
        int i = v.getId();

        if (i == R.id.btnSubmit) {
            signIn();
        } else if (i == R.id.btnCancel) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else if (i == R.id.btnRegister) {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        }
    }
}