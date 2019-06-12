package com.example.majdh.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditNoteActivity extends AppCompatActivity
{
    EditText title;
    EditText content;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = (EditText)findViewById(R.id.titleET);
        content = (EditText)findViewById(R.id.contentET);

        note = (Note)getIntent().getSerializableExtra("selected_note");

        title.setText(note.getTitle());
        content.setText(note.getContent());
    }

    public void updateNoteBtn_handle(View view)
    {
        TextView message = (TextView)findViewById(R.id.editNote_msg_TV);
        note.setTitle(title.getText().toString());
        note.setContent(content.getText().toString());
        note.updateNoteInDB(this);
        message.setText("Note changes has been saved.");
    }

    public void deleteNoteBtn_handle(View view)
    {
        note.deleteNoteFromDB(this);
        NotesActivity.setPopMsg(true);
        finish();
    }
}
