package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        ImageButton register = (ImageButton) findViewById(R.id.register);
        db=openOrCreateDatabase("UserDetails", Context.MODE_PRIVATE,null);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Cursor c = db.rawQuery("Select * from Users where name = ?",new String[]{email.getText().toString()});
                if(c.moveToFirst()) {
                    Toast.makeText(getApplicationContext(), "Already Registered with this email!", Toast.LENGTH_LONG).show();
                    name.setText("");
                    password.setText("");
                    email.setText("");

                }
                else{
                    if((name.getText().toString().equals("")) || email.getText().toString().equals("") || password.getText().toString().equals("") ){
                        Toast.makeText(getApplicationContext(),"Please Enter valid details",Toast.LENGTH_LONG).show();
                    }
                    else {
                        db.execSQL("insert into Users values(?,?,?)",new String[] {name.getText().toString(),email.getText().toString(),password.getText().toString()});
                        Cursor p = db.rawQuery("Select * from Users where name = ?",new String[]{name.getText().toString()});
                        if(p.moveToFirst()) {
                            Toast.makeText(getApplicationContext(), c.getString(0)+"Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent homeIntent = new Intent(getApplicationContext(),LearnHome.class);
                            startActivity(homeIntent);

                        }
                    }


                }
            }
        });


    }
}