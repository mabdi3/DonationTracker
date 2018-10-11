package com.example.abdim.donationtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

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
    }

    private void checkLogin(String userName, String userPassword) {
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
}
