package com.example.lab5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("username", "").equals("")) {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("name", sharedPreferences.getString("username", ""));
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void loginClick (View view) {

        EditText name = findViewById(R.id.nameInput);
        String username = name.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5_1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();

        Intent intent = new Intent(view.getContext(), MainActivity2.class);
        intent.putExtra("name", username);
        startActivity(intent);
    }
}