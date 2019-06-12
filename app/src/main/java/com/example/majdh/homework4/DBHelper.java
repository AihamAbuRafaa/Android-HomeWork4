package com.example.majdh.homework4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db";

    private static final String CREATE_ACCOUNTS_TABLE_QUERY =
            "CREATE TABLE " +
                    Account.TABLE_NAME + " (" +
                    Account._ID + " INTEGER PRIMARY KEY," +
                    Account.COLUMN_EMAIL + " TEXT," +
                    Account.COLUMN_PASSWORD + " TEXT);";

    private static final String CREATE_NOTES_TABLE_QUERY =
            "CREATE TABLE " +
                    Note.TABLE_NAME + " (" +
                    Note._ID + " INTEGER PRIMARY KEY," +
                    Note.COLUMN_TITLE + " TEXT," +
                    Note.COLUMN_CONTENT + " TEXT," +
                    Note.COLUMN_STATUS + " TEXT," +
                    Note.COLUMN_ON_DATE + " LONG);";

    private static final String CREATE_DONATIONS_TABLE_QUERY =
            "CREATE TABLE " +
                    Donations.TABLE_NAME + " (" +
                    Donations._ID + " INTEGER PRIMARY KEY," +
                    Donations.COLUMN_MONEY + " REAL);";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_ACCOUNTS_TABLE_QUERY);
        db.execSQL(CREATE_NOTES_TABLE_QUERY);
        db.execSQL(CREATE_DONATIONS_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Account.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Donations.TABLE_NAME);
        onCreate(db);
    }
}
