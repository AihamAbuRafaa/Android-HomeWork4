package com.example.majdh.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.registerMsg);
        EditText email = (EditText)findViewById(R.id.emailET);
        EditText password = (EditText)findViewById(R.id.passwordET);
        EditText rePassword = (EditText)findViewById(R.id.rePasswordET);
        if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || rePassword.getText().toString().isEmpty())
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("Please fill out all fields.");
            return;
        }
        else if(!isValidEmail(email.getText().toString()))
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("Incorrect email format.");
            return;
        }
        else if(!password.getText().toString().equals(rePassword.getText().toString()))
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("password and Confirm password do not match!");
            return;
        }
        Account new_account = new Account(email.getText().toString(), password.getText().toString());
        if(new_account.isAlreadyExist(this))
        {
            message.setTextColor(getResources().getColor(R.color.red));
            message.setText("This email is already exists.");
            return;
        }
        new_account.insertAccountToDB(this);
        message.setTextColor(getResources().getColor(R.color.green));
        message.setText("New account successfully created.");
    }

    private boolean isValidEmail(CharSequence email)
    {
        if (TextUtils.isEmpty(email))
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
