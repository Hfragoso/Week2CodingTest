package com.example.heber.week2codingtestheber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.heber.week2codingtestheber.FeedReaderContract.FeedEntry;

public class ReadAllNotesActivity extends AppCompatActivity {
    private DBHelper helper;
    private SQLiteDatabase database;

    private TextView showNotesTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_notes);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        showNotesTV = (TextView) findViewById(R.id.tv_show_results);
    }

    public void readAllNotes(View view) {
        showNotesTV.setText("");
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_NAME_NOTE_TITLE,
                FeedEntry.COLUMN_NAME_NOTE_CONTENT
        };
        String selection = FeedEntry.COLUMN_NAME_NOTE_TITLE + " = ?";
//        String[] selectionArg = {
//                "Record title"
//        };

//        String sortOrder = FeedEntry.COLUMN_NAME_NOTE_CONTENT +"DESC";

        Cursor cursor = database.query(
                FeedEntry.TABLE_NAME,   //TABLE
                projection,             //projection
                null,                   //Selection (WHERE)
                null,                   //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort order
        );
        while(cursor.moveToNext()){
            long entryId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String entryNoteTile = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NOTE_TITLE));
            String entryNoteContent = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_NOTE_CONTENT));

//            showRecordsTV.append("id: " + entryId + " title: " + entryTile + " subtitle: " + entrySubltitle + "\n");


            //////STRING BUILDER
            //FASTER THAN USING STRINGS
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("Result: ");
            sBuilder.append("id: ");
            sBuilder.append(entryId);
            sBuilder.append(" title: ");
            sBuilder.append(entryNoteTile);
            sBuilder.append(" content: ");
            sBuilder.append(entryNoteContent);
            sBuilder.append("\n");

            showNotesTV.append(sBuilder);
        }
    }
}
