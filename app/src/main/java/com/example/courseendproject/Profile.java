package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courseendproject.Domain.User;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    SQLiteDatabase db;
    int id=1;
    TextView logout,name,mail;
    static ArrayList<User>de;
    public static class Details {
        public Details(ArrayList<User> details) {
            de=details;
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.Name);
        mail = (TextView)findViewById(R.id.Mail);
        name.setText(de.get(0).getUser());
        mail.setText(de.get(0).getMail());
        logout=(TextView)findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GetStarted.class);
                Toast.makeText(Profile.this, "Logged", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
       db = openOrCreateDatabase("Marks", Context.MODE_PRIVATE,null);
       db.execSQL("create table if not exists marks(name varchar, subject varchar,subtopic varchar ,marks Integer)");
       Cursor c = db.rawQuery("select * from Marks where name = ?", new String[]{de.get(0).getUser()});
       if(c.moveToFirst()) {
           TableLayout stk = (TableLayout) findViewById(R.id.table_main);
           TableRow tbrow0 = new TableRow(this);
           TextView tv0 = new TextView(this);
           tv0.setText(" Sl.No ");
           tv0.setTextColor(android.R.color.white);
           tbrow0.addView(tv0);
           TextView tv1 = new TextView(this);
           tv1.setText(" Subject ");
           tv1.setTextColor(android.R.color.white);
           tbrow0.addView(tv1);
           TextView tv2 = new TextView(this);
           tv2.setText("Subtopic");
           tv2.setTextColor(android.R.color.white);
           tbrow0.addView(tv2);
           TextView tv3 = new TextView(this);
           tv3.setText(" Marks ");
           tv3.setTextColor(android.R.color.white);
           tbrow0.addView(tv3);
           stk.addView(tbrow0);
           while (c.moveToNext()) {
               TableRow tbrow = new TableRow(this);
               TextView t1v = new TextView(this);
               t1v.setText(id);
               t1v.setTextColor(android.R.color.white);
               t1v.setGravity(Gravity.CENTER);
               tbrow.addView(t1v);
               TextView t2v = new TextView(this);
               t2v.setText(c.getString(1));
               t2v.setTextColor(android.R.color.white);
               t2v.setGravity(Gravity.CENTER);
               tbrow.addView(t2v);
               TextView t3v = new TextView(this);
               t3v.setText(c.getString(2));
               t3v.setTextColor(android.R.color.white);
               t3v.setGravity(Gravity.CENTER);
               tbrow.addView(t3v);
               TextView t4v = new TextView(this);
               t4v.setText(Integer.toString(c.getInt(3)));
               t4v.setTextColor(android.R.color.white);
               t4v.setGravity(Gravity.CENTER);
               tbrow.addView(t4v);
               stk.addView(tbrow);
               id+=1;
           }
       }

    }
}