package com.example.abdim.donationtracker.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.text.TextUtils;
import android.util.Log;
import android.support.annotation.NonNull;

import android.widget.Toast;


import com.example.abdim.donationtracker.R;
import com.example.abdim.donationtracker.models.Account;
import com.example.abdim.donationtracker.models.AccountType;


import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

/**
 * RegisterActivity
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private EditText emailField;
    private EditText passField;
    private Spinner accountTypeField;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    /*
     * Check to see if passwords match, if username filled out to enable register button
     */
    /*
    private void enableRegister() {

        boolean isReady = username.getText().toString().length() >= USERNAME_MIN_LENGTH
                && password.getText().toString().length() > 0
                && confirmPassword.getText().toString().equals(password.getText().toString());
        register.setEnabled(isReady);
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.new_username);
        passField = findViewById(R.id.new_password);
        // EditText confirmPassField = findViewById(R.id.confirm_password);
        accountTypeField = findViewById(R.id.account_type_spinner);

        FirebaseDatabase mDatabaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mDatabaseInstance.getReference();
        mAuth = FirebaseAuth.getInstance();

        Button btnBack = findViewById(R.id.back_button);
        Button btnRegister = findViewById(R.id.register_button);

        btnBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        /*
          Set up the adapter to display the allowable AccountTypes in the spinner
         */
        ArrayAdapter<AccountType> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, AccountType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeField.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "current user: " + mAuth.getCurrentUser());

        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
            /*
            Log.d(TAG, "signing out");
            mAuth.signOut();
            */
        }
    }

    private void register() {
        Log.d(TAG, "register");

        if (!validateForm()) {
            return;
        }

        Editable userNameEditText = emailField.getText();
        Editable passFieldEditText = passField.getText();
        String username = userNameEditText.toString();
        String password = passFieldEditText.toString();

        Log.d(TAG, "username: " + username);
        Log.d(TAG, "password: " + password);
        mAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d(TAG, "createUser:onComplete: " + task.isSuccessful());
                    // Log.d(TAG, "onComplete" + task.getException().getMessage());
//                    if (task.getException() != null) {
//                        Log.d(TAG, "onComplete EXCEPTION " + task.getException().getMessage());
//                    }
                    if (task.isSuccessful()) {
                        AuthResult result = Objects.requireNonNull(task).getResult();
                        onAuthSuccess(Objects.requireNonNull(result).getUser());
                    } else {
                        Toast.makeText(RegisterActivity.this, "Sign Up Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

    private void onAuthSuccess(UserInfo user) {
        String username = usernameFromEmail(Objects.
                requireNonNull(Objects.requireNonNull(user).getEmail()));
        String password = passField.getText().toString();
        AccountType accountType = (AccountType) accountTypeField.getSelectedItem();

        // Write new user
        String userEmail = Objects.requireNonNull(user).getEmail();
        writeNewUser(user.getUid(), username, userEmail, password, accountType);

        // Notify user
        Toast.makeText(RegisterActivity.this, "Sign Up Successful",
                Toast.LENGTH_SHORT).show();
        // Go to MainActivity
        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        finish();
    }

    private void writeNewUser(String userId, String username, String email, String pass,
                              AccountType accountType) {

        Account account = new Account(username, pass, accountType, email);
        DatabaseReference childRef = mDatabase.child("users");
        childRef.child(userId).setValue(account);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
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
        int i = v.getId();

        if (i == R.id.register_button) {
            register();
        } else if (i == R.id.back_button) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }
    }

}

        /*
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if (!(username.getText().toString().length() >= USERNAME_MIN_LENGTH)) {
                    username.setError("Username must be " + USERNAME_MIN_LENGTH +
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
                if (password.getText().toString().length() < 4) {
                    password.setError("Password must be " + PASSWORD_MIN_LENGTH
                        + " characters or greater");
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
*/
