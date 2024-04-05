package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class GetStarted extends AppCompatActivity {
    private Databasehelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        AppCompatButton getstart = (AppCompatButton) findViewById(R.id.getstart);

        db = new Databasehelper(GetStarted.this);

        try {
            db.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase pp = db.openDataBase();

        getstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i1);
            }
        });
    }
}