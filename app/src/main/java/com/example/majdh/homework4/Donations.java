package com.example.majdh.homework4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

public class Donations implements BaseColumns
{
    //database accounts table data
    public static final String TABLE_NAME = "Donations";
    public static final String COLUMN_MONEY = "Money";

    private double donate_money;

    public Donations()
    { }

    public Donations(double num)
    {
        this.donate_money = num;
    }

    public void insertDonateToDB(Context context)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONEY, donate_money);
        new DBHelper(context).getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public double getDonate_money()
    {
        return donate_money;
    }

    public void setDonate_money(double donate_money)
    {
        this.donate_money = donate_money;
    }

    public double getDonations_recFromDB(Context context)
    {
        double money_sum = 0;
        String[] donations = { COLUMN_MONEY };
        Cursor c = new DBHelper(context).getReadableDatabase().query(TABLE_NAME, donations, null, null, null, null, null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            money_sum += c.getDouble(c.getColumnIndex(COLUMN_MONEY));
            c.moveToNext();
        }
        return money_sum;
    }
}
