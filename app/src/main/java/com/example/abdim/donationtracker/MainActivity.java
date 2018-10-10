package com.example.abdim.donationtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public boolean loggedIn = false;

    private int loginAttemptsRemaining = 5;
    private EditText Name;
    private EditText Password;
    private Button Submit;
    private Button Cancel;
    private Button Register;
    private TextView Info;
    private static RegisteredAccounts registeredAccounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registeredAccounts = new RegisteredAccounts();
        Name = (EditText)findViewById(R.id.etUser);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.textLoginInfo);
        Submit = (Button)findViewById(R.id.btnSubmit);
        Cancel = (Button)findViewById(R.id.btnCancel);
        Register = (Button)findViewById(R.id.registrationButton);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(Name.getText().toString(), Password.getText().toString());
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(cancel);
                finish();
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Info.setText("Login Attempts Remaining: 5");

        readSDFile();
    }

    private void checkLogin(String userName, String userPassword) {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
        for (Account account : RegisteredAccounts.getAccountStorage()) {
            if (userName.equals(account.getUsername()) && userPassword.equals(account.getPass())) {
                loggedIn = true;

            }
        }
        if (loggedIn) {
            Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(intent);
            finish();
        } else {

            loginAttemptsRemaining--;

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Login Failure");
            alertDialog.setMessage("Login credentials were incorrect, " + loginAttemptsRemaining
                    + " attempts remaining.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            Info.setText("Login Attempts Remaining: " + String.valueOf(loginAttemptsRemaining));
            if (loginAttemptsRemaining == 0) {
                Submit.setEnabled(false); // disable button
            }
        }
    }

    public static RegisteredAccounts getRegisteredAccounts() {
        return registeredAccounts;
    }


    /**
     * Opens the LocationData.csv file in the /res/raw directory
     *
     * Key,Name,Latitude,Longitude,Street Address,City,State,Zip,Type,Phone,Website
     * Line Entry format:
     *  [0] = key
     *  [1] = name
     *  [2] = latitude
     *  [3] = longitude
     *  [4] = address
     *  [5] = city
     *  [6] = state
     *  [7] = zip
     *  [8] = type
     *  [9] = phone
     *  [10] = website
     */
    private void readSDFile() {
        Locations model = new Locations();

        try {
            //open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.LocationData);
            //from here we probably should call a model method and pass the inputstream
            //wrap it in a BufferedReader so that we get the readLine() method

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine();
            while((line = br.readLine()) != null) {
                Log.d(MainActivity.TAG, line);
                String[] tokens = line.split(",");
                Location newLocal = new Location(Integer.parseInt(tokens[0]), tokens[1], LocationType.DROPOFFONLY, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[2]), tokens[4] + ", " + tokens[5] + ", " + tokens[6] + " " + tokens[7], tokens[9], tokens[10]);
                if (tokens[8].equals("Store")) {
                    newLocal.setLocationType(LocationType.STORE);
                } else if (tokens[8].equals("Warehouse")) {
                    newLocal.setLocationType(LocationType.WAREHOUSE);
                }
                model.addLocation(newLocal);
            }
            br.close();
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error reading assets", e);
        }
    }
}
