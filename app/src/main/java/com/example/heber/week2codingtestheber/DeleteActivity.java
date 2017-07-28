package com.example.heber.week2codingtestheber;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heber.week2codingtestheber.FeedReaderContract.FeedEntry;

public class DeleteActivity extends AppCompatActivity {

    private DBHelper helper;
    private SQLiteDatabase database;

    private TextView showResultsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        showResultsTV = (TextView) findViewById(R.id.tv_show_results);
    }

    public void deleteNote(View view) {
        String deletingTitle =  "";
        Intent intent = getIntent();
        if(intent != null){
            deletingTitle = intent.getStringExtra(MainActivity.MAIN_ACTIVITY_EXTRA);
        }

        String selection = FeedEntry.COLUMN_NAME_NOTE_TITLE + " LIKE ?";
        String[] selectionArgs = {
                deletingTitle
        };

        int deleted = database.delete(
                FeedEntry.TABLE_NAME,
                selection,
                selectionArgs
        );

        if(deleted > 0){
            Toast.makeText(this, "Title: " + deletingTitle + " has been deleted", Toast.LENGTH_SHORT).show();
            showResultsTV.setText("Result: Title: " + deletingTitle + " has been deleted");
        }else{
            Toast.makeText(this, "deleteRecord: record not deleted", Toast.LENGTH_SHORT).show();
            showResultsTV.setText("Result: deleteRecord: record not deleted");
        }
    }
}
