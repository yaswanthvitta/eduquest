package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.courseendproject.Domain.User;

import java.util.ArrayList;

public class Exam extends AppCompatActivity {
    Button b;
    SQLiteDatabase datamarks;
    RadioGroup rg;
    TextView t1;
//    ArrayList<User> a;
    TextView t2;
    ProgressBar pg;
    private  Databasehelper db;
    RadioButton op1,op2,op3,op4;


    int secondsRemaining=60,marks=0;


    Cursor c;
    public int count=0;

    CountDownTimer mytimer;

    boolean flag=false;

    static ArrayList<User> de;

    private MainActivity m;
    String subtopic,subject,marks1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        rg=(RadioGroup)findViewById(R.id.rg);
        op1= (RadioButton)findViewById(R.id.op1);
        op2= (RadioButton)findViewById(R.id.op2);
        op3= (RadioButton)findViewById(R.id.op3);
        op4= (RadioButton)findViewById(R.id.op4);
        b=(Button)findViewById(R.id.sub);
        t1 = (TextView) findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2) ;
        rg =(RadioGroup)findViewById(R.id.rg);
        pg=(ProgressBar)findViewById(R.id.pg);
        Intent i1 =getIntent();

        subtopic = i1.getStringExtra("subtopic");
        subject = i1.getStringExtra("subject");
        db = new Databasehelper(Exam.this);

        try {
            db.createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase pp = db.openDataBase();
        c=pp.rawQuery("Select * from MadQuiz where subtopic = ? ",new String[]{i1.getStringExtra("subtopic")});
        if(c.moveToFirst()){
            timer();
            t1.setText(c.getString(1));
            op1.setText(c.getString(2));
            op2.setText(c.getString(3));
            op3.setText(c.getString(4));
            op4.setText(c.getString(5));


        }
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mytimer.cancel();
                secondsRemaining=61;
                validate();

//                if(count>2){
//                    Toast.makeText(Exam.this, "successfully attempted "+ marks, Toast.LENGTH_SHORT).show();
//
//                }
//                next();




            }
        });


    }


   public void timer(){
        mytimer =new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsRemaining--;
                t2.setText(Integer.toString(secondsRemaining));
                pg.setProgress(secondsRemaining);
            }

            @Override
            public void onFinish() {
                mytimer.cancel();
                secondsRemaining=61;
                validate();
            }
        }.start();
   }

    public static class Userd {
        public Userd(ArrayList<User> details) {
            de=details;
        }

    }
        public void validate(){
            count++;
            int id = rg.getCheckedRadioButtonId();
            RadioButton rb= (RadioButton) findViewById(id);

            int number = Integer.parseInt(c.getString(6));
            if(c.getString(number+1).equals(rb.getText())){
                marks+=1;
                Toast.makeText(this, "Correct Answer "+count , Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Wrong Answer" , Toast.LENGTH_SHORT).show();
            }

            if (count<3){
                next();
            }
            else{

                Toast.makeText(Exam.this, "successfully attempted "+ marks +subject+subtopic, Toast.LENGTH_SHORT).show();
                datamarks = openOrCreateDatabase("Marks", Context.MODE_PRIVATE,null);
                datamarks.execSQL("insert into marks values(?,?,?,?)",new String[]{de.get(0).getUser(),"MAD",subtopic,Integer.toString(marks) });
                Intent end = new Intent(getApplicationContext(), MAD.class);
                startActivity(end);
            }

        }
        public void next(){



            timer();

            if(count != c.getCount()-1){

                c.moveToNext();

                t1.setText(c.getString(1));
                op1.setText(c.getString(2));
                op2.setText(c.getString(3));
                op3.setText(c.getString(4));
                op4.setText(c.getString(5));

            }

            else if(count==2){
                flag=true;
                c.moveToNext();
                b.setText("Submit");
                t1.setText(c.getString(1));
                op1.setText(c.getString(2));
                op2.setText(c.getString(3));
                op3.setText(c.getString(4));
                op4.setText(c.getString(5));


            }


        }


}


