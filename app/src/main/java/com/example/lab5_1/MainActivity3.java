package com.example.lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteid = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText noteTextBox = (EditText) findViewById(R.id.noteListView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteid = extras.getInt("noteid");
        }

        if (noteid != -1) {
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            noteTextBox.setText(noteContent);
        }

    }

    public void onSaveClick(View view) {
        EditText noteTextBox = (EditText) findViewById(R.id.noteListView);

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(title, date, noteTextBox.getText().toString(), username);
        }
        else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, noteTextBox.getText().toString(), username);
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        intent.putExtra("name", username);
        startActivity(intent);

    }
}