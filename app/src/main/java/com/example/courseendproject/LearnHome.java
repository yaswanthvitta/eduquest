package com.example.courseendproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.courseendproject.Adapter.TrendsAdapter;
import com.example.courseendproject.Domain.TrendSDomain;
import com.example.courseendproject.Domain.User;

import java.util.ArrayList;

public class LearnHome extends AppCompatActivity {
    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;

    TextView welcome;
    ImageButton profile_Image;
    static ArrayList<User> de;
    public static class Details {
        public Details(ArrayList<User> details) {
            de=details;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_home);
        initRecyclerView();
        welcome= (TextView) findViewById(R.id.Welcome);
        profile_Image = (ImageButton) findViewById(R.id.Profile_Image);
        welcome.setText( "Hello  "+de.get(0).getUser());
        profile_Image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }

        });



    }

    private void initRecyclerView() {
        ArrayList<TrendSDomain> items = new ArrayList<>();
        items.add(new TrendSDomain("MAD","Using Java","trends",new View.OnClickListener(){
            public void onClick(View v){
                Intent i1 =new Intent(getApplicationContext(),MAD.class);
                startActivity(i1);
            }
        }));
        items.add(new TrendSDomain("titel","subtitele","trends2", new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(i);
            }
        }));
        items.add(new TrendSDomain("titel","subtitele","trends3", new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(i);
            }
        }));

        recyclerViewTrends = findViewById(R.id.view);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterTrendsList = new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);

    }
}