package com.example.coldcalling;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Called extends AppCompatActivity {
    private ListView students,number;
    private Button back;
    private ArrayList <String> names,called,times;
    private int [] x;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_called);
        Bundle extras = getIntent().getExtras();
        back = findViewById(R.id.home);
        students=findViewById(R.id.names);
        number=findViewById(R.id.timesCalled);
        names=extras.getStringArrayList("names");
        x=extras.getIntArray("calledList");
        called= new ArrayList<>();
        times= new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            if (x[i] >0 ){
                called.add(names.get(i));
                times.add(""+x[i]);
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
        ArrayAdapter<String> listCalled=new ArrayAdapter<String>(this, R.layout.custom_text,times){
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
        number.setAdapter(listCalled);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }});
    }


}