package com.example.majdh.homework4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

public class Account implements BaseColumns
{
    //database accounts table data
    public static final String TABLE_NAME = "Accounts";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";


    public String email, password;

    public Account(String emaill, String password)
    {
        this.email = emaill;
        this.password = password;
    }

    public void insertAccountToDB(Context context)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        new DBHelper(context).getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public boolean isAlreadyExist(Context context)
    {
        String[] emails = { COLUMN_EMAIL };
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = { email };
        Cursor c = new DBHelper(context).getReadableDatabase().query(TABLE_NAME, emails, selection, selectionArgs, null, null, null);
        return c.getCount()<=0 ? false : true;
    }

    public String getPasswordFromDB(Context context)
    {
        String[] passwords = { COLUMN_PASSWORD };
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = { email };
        Cursor c = new DBHelper(context).getReadableDatabase().query(TABLE_NAME, passwords, selection, selectionArgs, null, null, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(COLUMN_PASSWORD));
    }

}
