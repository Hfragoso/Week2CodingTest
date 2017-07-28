package com.example.heber.week2codingtestheber;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heber.week2codingtestheber.FeedReaderContract.FeedEntry;

public class UpdateActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase database;

    private EditText noteTitleET;
    private EditText noteContentET;
    private TextView showResultsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        noteTitleET = (EditText) findViewById(R.id.et_note_title);
        noteContentET = (EditText) findViewById(R.id.et_note_content);
        showResultsTV = (TextView) findViewById(R.id.tv_show_results);
    }

    public void updateNote(View view) {
        String newTitle = noteTitleET.getText().toString();
        String newContent = noteContentET.getText().toString();
        String currentTitle = "";
        Intent intent = getIntent();
        if(intent != null){
            currentTitle = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);
        }

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NOTE_TITLE, newTitle);
        values.put(FeedEntry.COLUMN_NAME_NOTE_CONTENT, newContent);
        String selection = FeedEntry.COLUMN_NAME_NOTE_TITLE + " LIKE ?";
        String[] selectionArgs = {
                currentTitle
        };

        int count = database.update(
                FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        if(count > 0){
            Toast.makeText(this, "updateNote: Updated notes " + count, Toast.LENGTH_SHORT).show();
            showResultsTV.setText("Result: updateNote: Updated notes " + count);
            noteTitleET.setText("");
            noteContentET.setText("");
            showResult();
        }else{
            Toast.makeText(this, "updateNote: notes not updated", Toast.LENGTH_SHORT).show();
            showResultsTV.setText("Result: updateNote: notes not updated" + count);
        }
    }

    private void showResult() {
        //No implementation yet
    }
}
