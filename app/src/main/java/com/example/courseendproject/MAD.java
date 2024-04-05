package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MAD extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner components;
    String Mad="MAD";
    String[] comp = {"Components ", "Layout", "Text", "Widget"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad);
        components = (Spinner) findViewById(R.id.components);
        components.setOnItemSelectedListener(this);
        ArrayAdapter a = new ArrayAdapter(this,R.layout.color_dropdown_spinner,comp);
        a.setDropDownViewResource(R.layout.color_dropdown_spinner);
        components.setAdapter(a);
        TextView introduction = (TextView) findViewById(R.id.introduction);
        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent topic = new Intent(getApplicationContext(), Topic.class);
                topic.putExtra("subtopic", "Introduction");
                startActivity(topic);
            }
        });


        //Quiz
//        db.execSQL("create table if not exists Quiz(subtopic varchar,question varchar,option1 varchar,option2 varchar,option3 varchar,option4 varchar,answer number )");
//        db.execSQL("insert into Quiz values('Introduction','What is the primary purpose of a layout in mobile app development?','A) Managing user authentication','B) Organizing and arranging UI elements','C) Handling backend server requests','D) Implementing data storage',2)");
//        db.execSQL("insert into Quiz values('Layout','What is the primary purpose of a layout in mobile app development?','A) Managing user authentication','B) Organizing and arranging UI elements','C) Handling backend server requests','D) Implementing data storage',2)");
//        db.execSQL("insert into MadQuiz values ('Introduction','Which version control system is widely used in mobile app development for managing source code?','A) Git','B) SVN','C) Mercurial','D) Perforce',1)");
////        db.execSQL("insert into MadQuiz values('Introduction','What do iOS and Android represent in the context of mobile app development?','A) Internet Operating Systems','B) Innovative Operating Systems','C) Apples and Googles mobile platforms','D) Internal Operating Systems',3)");
//        db.execSQL("insert into MadQuiz values('Layout','Which type of layout arranges UI elements in a linear fashion, either horizontally or vertically?','A) Relative Layout','B) Frame Layout','C) Linear Layout','D) Constraint Layout',3)");
//        db.execSQL("insert into MadQuiz values('Layout','What is the key advantage of using Constraint Layout in Android app development?','A) Simplifies UI testing','B) Provides a flexible grid system','C) Allows for complex UI designs with defined constraints','D) Optimizes battery usage',3)");
//        db.execSQL("insert into MadQuiz values('Widget','What is the primary purpose of widgets in mobile application development?','A) Manage server-side interactions','B) Organize UI layouts','C) Facilitate user interaction and engagement','D) Handle app deployment strategies',3)");
//        db.execSQL("insert into MadQuiz values('Widget','Which of the following is a common widget used for displaying images in Android development?','A)ImageBar','B) ImagePanel','C) ImageView','D) ImageContainer',3)");
//        db.execSQL("insert into MadQuiz values('Widget','In iOS development, what is the equivalent term for widgets that facilitate user interaction, such as buttons and text fields?','A) Views','B)B) Controls','C) Widgets (same term)','D)Elements',2)");
//        db.execSQL("insert into MadQuiz values('Text','What is the primary role of text in mobile application development?','A) Handling server-side processes','B) Enhancing visual aesthetics','C) Facilitating user interaction and communication','D) Managing app deployment strategies',3)");
//        db.execSQL("insert into MadQuiz values('Text','Which component in Android is commonly used for displaying and manipulating formatted text in the user interface?','A) TextView','B) TextBar','C) TextPanel','D) LabelView',1)");
//        db.execSQL("insert into MadQuiz values('Text','What does localization aim to achieve in mobile app development concerning text?','A) Enhancing app security','B) Adapting text to different languages and regions','C) Controlling UI layouts','D) Optimizing battery usage',2)");
    }

    @Override
    public void onItemSelected(AdapterView<?> a, View view, int position, long id) {
              if(position !=0) {
                  Intent topic = new Intent(getApplicationContext(), Topic.class);
                  Bundle extras = new Bundle();
                  extras.putString("subtopic", comp[position]);
                  extras.putString("subject",Mad);
                  topic.putExtras(extras);
                  startActivity(topic);
             }

    }
    @Override
    public void onNothingSelected(AdapterView<?> a) {

    }
}
