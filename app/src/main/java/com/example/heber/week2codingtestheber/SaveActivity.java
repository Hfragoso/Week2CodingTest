package com.example.heber.week2codingtestheber;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heber.week2codingtestheber.FeedReaderContract.FeedEntry;

public class SaveActivity extends AppCompatActivity {
    private DBHelper helper;
    private SQLiteDatabase database;

    private EditText noteTitleET;
    private EditText noteContentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        noteTitleET = (EditText) findViewById(R.id.et_note_title);
        noteContentET = (EditText) findViewById(R.id.et_note_content);
    }

    public void saveNote(View view) {
        String noteTitle = noteTitleET.getText().toString();
        String noteContent = noteContentET.getText().toString();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NOTE_TITLE, noteTitle);
        values.put(FeedEntry.COLUMN_NAME_NOTE_CONTENT, noteContent);

        long recordId = database.insert(
                FeedEntry.TABLE_NAME,
                null,
                values
        );
        if(recordId > 0){
            Toast.makeText(this, "SaveNote: Note saved.", Toast.LENGTH_SHORT).show();
            noteTitleET.setText("");
            noteContentET.setText("");
        }else{
            Toast.makeText(this, "SaveNote: Note not saved", Toast.LENGTH_SHORT).show();
        }

    }
}
