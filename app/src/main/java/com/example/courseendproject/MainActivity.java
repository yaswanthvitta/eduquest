package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.courseendproject.Domain.User;
import com.example.courseendproject.Exam;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity  {

    EditText email;
    EditText password;

    ImageButton login;
    SQLiteDatabase db;

    ArrayList<User> details;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        db=openOrCreateDatabase("UserDetails", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists Users(name varchar,email varchar,password varchar)");
        email =(EditText) findViewById(R.id.email);
        password =(EditText) findViewById(R.id.password);
        login = (ImageButton) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("select * from Users where email =?",new String[] {email.getText().toString()});




                if (c.moveToFirst()){
                    if (c.getString(2).equals(password.getText().toString())){
                        details= new ArrayList<>();
                        details.add(new User(c.getString(0),c.getString(1)));

                        new Exam.Userd(details);
                        new Profile.Details(details);
                        new LearnHome.Details(details);
                        Intent homeIntent =new Intent(getApplicationContext(), LearnHome.class);
                        startActivity(homeIntent);
                    }
                    else {
                        password.setText("");
                        Toast.makeText(getApplicationContext(),c.getString(2),Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    email.setText("");
                    password.setText("");
                    Toast.makeText(getApplicationContext(),"User Not Registered!",Toast.LENGTH_LONG).show();

                }

            }

        });

        TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(registerIntent);
            }
        });

    }



}