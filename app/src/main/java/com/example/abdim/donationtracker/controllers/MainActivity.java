package com.example.abdim.donationtracker.controllers;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private int loginAttemptsRemaining = 5;
    private EditText emailField;
    private EditText passField;
    private TextView loginInfo;

    private Button btnSubmit;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.etUser);
        passField = findViewById(R.id.etPassword);
        loginInfo = findViewById(R.id.textLoginInfo);

        Button btnCancel = findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);
        Button btnRegister = findViewById(R.id.btnRegister);

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

        switch(i) {
            case R.id.btnSubmit : {
                signIn();
            }
            break;
            case R.id.btnCancel : {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
            break;
            case R.id.btnRegister : {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
            break;
            default : {
            }
        }
    }
}