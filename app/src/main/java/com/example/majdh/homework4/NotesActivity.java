package com.example.majdh.homework4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NotesActivity extends AppCompatActivity
{
    private ArrayList<Note> notes;
    private final int ADD_NEW_NOTE_REQUEST = 1;
    private final int EDIT_NOTE_REQUEST = 2;
    private final int NOTE_LIFE = 2*24*60*60*1000;
    private static boolean isPopMsg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        updateNotesList();
        updateNotes();
        updateNotesList();
    }

    public static void setPopMsg(boolean popMsg)
    {
        isPopMsg = popMsg;
    }

    private void updateNotes()
    {
        for(Note n : notes)
        {
            if(System.currentTimeMillis() - n.getOn_date().getTime() >= NOTE_LIFE)
            {
                n.setStatus("Received");
                n.updateNoteInDB(this);
            }
        }
    }

    public void updateNotesList()
    {
        ListView notes_LV = (ListView)findViewById(R.id.notesLV);
        TextView notes_num = (TextView)findViewById(R.id.notes_num_TV);

        Note n = new Note();
        notes = n.getNotesFromDB(this);
        notes_num.setText(notes.size()+"");

        NotesAdapter notesAdapter = new NotesAdapter(this, notes);
        notes_LV.setAdapter(notesAdapter);

        notes_LV.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Note selected_note = notes.get(position);
                if(selected_note.getStatus().equals("Sent"))
                {
                    Intent editeNote = new Intent(getApplicationContext(), EditNoteActivity.class);
                    editeNote.putExtra("selected_note", selected_note);
                    startActivityForResult(editeNote, EDIT_NOTE_REQUEST);
                }
                else
                {
                    Toast.makeText(parent.getContext(), "Can't edit a received note!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addNoteBtn_handle(View view)
    {
        Intent newNote = new Intent(this, newNoteActivity.class);
        startActivityForResult(newNote, ADD_NEW_NOTE_REQUEST);
    }

    public void donateBtn_handle(View view)
    {
        Intent donate = new Intent(this, DonateActivity.class);
        startActivity(donate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        updateNotesList();
        updateNotes();
        updateNotesList();
        TextView message = (TextView)findViewById(R.id.notes_msg_TV);
        if(requestCode == ADD_NEW_NOTE_REQUEST && isPopMsg)
        {
            message.setText("New note has been successfully added.");
            isPopMsg = false;
        }
        else if(requestCode == EDIT_NOTE_REQUEST && isPopMsg)
        {
            message.setText("Note has been successfully deleted.");
            isPopMsg = false;
        }
    }
}
