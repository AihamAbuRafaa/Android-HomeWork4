package com.example.majdh.homework4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.LoginMsg);
        EditText email = (EditText)findViewById(R.id.emailET);
        EditText password = (EditText)findViewById(R.id.passwordET);
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
        {
            message.setText("Please fill out all fields.");
            return;
        }
        Account check_account = new Account(email.getText().toString(), password.getText().toString());
        if(check_account.isAlreadyExist(this) && check_account.getPasswordFromDB(this).equals(password.getText().toString()))
        {
            Intent notes = new Intent(this, NotesActivity.class);
            startActivity(notes);
        }
        else
            message.setText("Email or Password is incorrect");
    }

    public void registerBtn_handle(View view)
    {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    public void donateBtn_handle(View view)
    {
        Intent donate = new Intent(this, DonateActivity.class);
        startActivity(donate);
    }
}
