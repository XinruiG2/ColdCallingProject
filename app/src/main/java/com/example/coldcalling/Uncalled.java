package com.example.coldcalling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Uncalled extends AppCompatActivity{

    private Button back;
    private ListView students;
    private ArrayList <String> names,called;
    private int [] x;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncalled);
        back = findViewById(R.id.homee);
        Bundle extras = getIntent().getExtras();
        students=findViewById(R.id.uncalledPeople);
        names=extras.getStringArrayList("names");
        x=extras.getIntArray("calledList");
        called= new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            if (x[i] ==0 ){
                called.add(names.get(i));
            }
        }

        ArrayAdapter<String> listNames=new ArrayAdapter<String>(this, R.layout.custom_text,called){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if(position%2==1) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
                }
                else{
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                }
                return view;
            }
        };

        students.setAdapter(listNames);


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }});

    }



}