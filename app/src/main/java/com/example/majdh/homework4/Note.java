package com.example.majdh.homework4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.widget.TableLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Note implements BaseColumns, Serializable
{
    //database accounts table data
    public static final String TABLE_NAME = "Notes";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_CONTENT = "Content";
    public static final String COLUMN_STATUS = "Status";
    public static final String COLUMN_ON_DATE = "On_Date";


    private int id;
    private String title;
    private String content;
    private String status;
    private Date on_date;

    public Note()
    { }

    public Note(int id, String t, String c, String s, Date d)
    {
        this.id = id;
        this.title = t;
        this.content = c;
        this.status = s;
        this.on_date = d;
    }

    public int getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public String getStatus()
    {
        return status;
    }

    public String getTitle()
    {
        return title;
    }

    public Date getOn_date()
    {
        return on_date;
    }

    public void setId(int idd)
    {
        this.id = idd;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setOn_date(Date on_date)
    {
        this.on_date = on_date;
    }

    public void insertNoteToDB(Context context)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_STATUS, status);
        values.put(COLUMN_ON_DATE, on_date.getTime());
        new DBHelper(context).getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public ArrayList<Note> getNotesFromDB(Context context)
    {
        ArrayList<Note> notes_list = new ArrayList<Note>();
        String sortOrder = BaseColumns._ID + " DESC";
        Cursor c = new DBHelper(context).getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, sortOrder);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            notes_list.add(new Note(
                c.getInt(c.getColumnIndex(BaseColumns._ID)),
                c.getString(c.getColumnIndex(COLUMN_TITLE)),
                c.getString(c.getColumnIndex(COLUMN_CONTENT)),
                c.getString(c.getColumnIndex(COLUMN_STATUS)),
                new Date(c.getLong(c.getColumnIndex(COLUMN_ON_DATE)))
            ));
            c.moveToNext();
        }
        return notes_list;
    }

    public void updateNoteInDB(Context context)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_STATUS, status);
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { id+"" };
        new DBHelper(context).getWritableDatabase().update(TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteNoteFromDB(Context context)
    {
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { id+"" };
        new DBHelper(context).getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
    }
}
