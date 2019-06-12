package com.example.majdh.homework4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class newNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }

    public void addNewNoteBtn_handle(View view)
    {
        EditText title = (EditText)findViewById(R.id.titleET);
        EditText content = (EditText)findViewById(R.id.contentET);
        Note new_note = new Note(
            1, //not needed in our case
                title.getText().toString(),
                content.getText().toString(),
                "Sent",
                new Date(System.currentTimeMillis())
        );
        new_note.insertNoteToDB(this);
        NotesActivity.setPopMsg(true);
        finish();
    }
}
