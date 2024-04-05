package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Topic extends AppCompatActivity {
   private  Databasehelper db;
   TextView topic,content;
   Button takequiz;
   Intent i;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        takequiz = (Button)findViewById(R.id.takequiz);
        topic =(TextView)findViewById(R.id.topicname);
        content =(TextView)findViewById(R.id.content);
        i = getIntent();
        topic.setText(i.getStringExtra("subtopic"));

        WebView we =(WebView) findViewById(R.id.webview);
        db = new Databasehelper(Topic.this);

        try {
            db.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase pp = db.openDataBase();



        Cursor c = pp.rawQuery("Select * from MadTopics where subtopic= ?;",new String[] {i.getStringExtra("subtopic")});
        if(c.moveToFirst()){
            String vedio = c.getString(1);
            we.loadData(vedio,"text/html","utf-8");
            we.getSettings().setJavaScriptEnabled(true);
            we.setWebChromeClient(new WebChromeClient());
            content.setText(c.getString(2));

        }
        takequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), Exam.class );

                extras = i.getExtras();
                String subtopic=extras.getString("subtopic");
                String subject=extras.getString("subject");
                i1.putExtra("subtopic",subtopic);
                i1.putExtra("subject",subject);
                //Toast.makeText(Topic.this,subject, Toast.LENGTH_SHORT).show();
                startActivity(i1);
            }
        });

    }
}