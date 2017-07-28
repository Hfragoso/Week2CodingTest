package com.example.heber.week2codingtestheber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_ACTIVITY_EXTRA = "com.example.heber.week2codingtestheber.MAIN_ACTIVITY_EXTRA";

    private EditText updateDeleteET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateDeleteET = (EditText) findViewById(R.id.et_update_delete);
    }

    public void saveNote(View view) {
        Intent intent = new Intent(MainActivity.this, SaveActivity.class);
        startActivity(intent);
    }

    public void readAllNotes(View view) {
        Intent intent = new Intent(MainActivity.this, ReadAllNotesActivity.class);
        startActivity(intent);
    }

    public void updateNote(View view) {
        String update = updateDeleteET.getText().toString();
        if(update.isEmpty())
            Toast.makeText(this, "You need to write on Update/Delete box", Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            intent.putExtra(MAIN_ACTIVITY_EXTRA, update);
            startActivity(intent);
            updateDeleteET.setText("");
        }
    }

    public void deleteNote(View view) {
        String update = updateDeleteET.getText().toString();
        if(update.isEmpty())
            Toast.makeText(this, "You need to write on Update/Delete box", Toast.LENGTH_SHORT).show();
        else{
            String delete = updateDeleteET.getText().toString();
            Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
            intent.putExtra(MAIN_ACTIVITY_EXTRA, delete);
            startActivity(intent);
            updateDeleteET.setText("");
        }
    }
}