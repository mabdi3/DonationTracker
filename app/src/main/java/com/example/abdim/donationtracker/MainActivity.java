package com.example.abdim.donationtracker;

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
    private Button Login;
    private TextView Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etUser);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("Login Attempts Remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void checkLogin(String userName, String userPassword) {
        if (userName.equals("user") && userPassword.equals("pass")) {
            loggedIn = true;
            Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(intent);
        } else {
            loginAttemptsRemaining--;
            Info.setText("Login Attempts Remaining: " + String.valueOf(loginAttemptsRemaining));
            if (loginAttemptsRemaining == 0) {
                Login.setEnabled(false); // disable button
            }

        }
    }
}
