package com.example.coldcalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private TextView className, name;
    private Button random,cStudents,uCStudents;
    private ImageView id;
    private String classN = "App Dev";
    private int[] times, images;
    private long[] timeDiff, timeStart;
    private ArrayList<String> names;
    private ArrayList<String> min;
    TextView currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = new int[]{R.drawable.aamir_ali,R.drawable.adrian_yan,R.drawable.alex_aney,R.drawable.bipra_dey, R.drawable.daniel_dultsin,
                R.drawable.darren_dong, R.drawable.dennis_wang, R.drawable.dhruv_amin, R.drawable.eden_kogan, R.drawable.eli_bui,
                R.drawable.elie_belkin, R.drawable.evelyn_paskhaver, R.drawable.fardin_iqbal, R.drawable.jerry_he, R.drawable.kenny_cao,
                R.drawable.marc_rosenberg, R.drawable.matthew_chen, R.drawable.michael_wu, R.drawable.ming_lin, R.drawable.mohammed_ihtisham,
                R.drawable.noam_canter, R.drawable.ralf_pacia, R.drawable.samuel_iskhakov, R.drawable.sean_kerrigan, R.drawable.sebastian_marinescu,
                R.drawable.selina_li, R.drawable.shuyue_chen, R.drawable.tanushri_sundaram, R.drawable.vasu_patel, R.drawable.xinrui_ge,
                R.drawable.zhian_maysoon};
        names = new ArrayList<>();
        currentDate = findViewById(R.id.date);
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        currentDate.setText(formattedDate);
        names.add("Aamir");
        names.add("Adrian");
        names.add("Alex");
        names.add("Bipra");
        names.add("Daniel");
        names.add("Darren");
        names.add("Dennis");
        names.add("Dhruv");
        names.add("Eden");
        names.add("Eli");
        names.add("Elie");
        names.add("Evelyn");
        names.add("Fardin");
        names.add("Jerry");
        names.add("Kenny");
        names.add("Marc");
        names.add("Matthew");
        names.add("Micheal");
        names.add("Ming");
        names.add("Mohammed");
        names.add("Noam");
        names.add("Ralf");
        names.add("Samuel");
        names.add("Sean");
        names.add("Sebastian");
        names.add("Selina");
        names.add("Shuyue");
        names.add("Tanushri");
        names.add("Vasu");
        names.add("Xinrui");
        names.add("Zhian");
        times = new int[images.length];
        timeDiff = new long[images.length];
        timeStart = new long[images.length];
        random = findViewById(R.id.button);
        id = findViewById(R.id.picture);
        name = findViewById(R.id.textView2);
        className = findViewById(R.id.className);
        cStudents=findViewById(R.id.called);
        uCStudents=findViewById(R.id.uncalled);
        className.setText(classN);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = (int) (Math.random() * images.length);
                if (times[num] == 0) {
                    timeDiff[num] = System.currentTimeMillis();
                    called(num);
                } else if (times[num]==1){
                    timeStart[num]=System.currentTimeMillis();
                    if (timeStart[num] - timeDiff[num] > 2400000) {
                        timeDiff[num]=timeStart[num];
                        times[num]--;
                    }
                    called(num);
                }
                else{
                    timeStart[num]=System.currentTimeMillis();
                    if (timeStart[num] - timeDiff[num] < 2400000) {
                        timeDiff[num] = System.currentTimeMillis();
                        view.callOnClick();
                    }
                    else{
                        timeDiff[num]=timeStart[num];
                        called(num);
                    }
                }

            }
        });
        cStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Called.class);
                i.putExtra("calledList", times);
                i.putExtra("drawables", images);
                i.putExtra("names", names);
                startActivity(i);
            }});
        uCStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Uncalled.class);
                i.putExtra("calledList", times);
                i.putExtra("drawables", images);
                i.putExtra("names", names);
                startActivity(i);
            }});
    }
    public void called ( int num){
        id.setImageResource(images[num]);
        //get name of file, placeholder
        name.setText("" + names.get(num));
        //keep count of # times student been called
        times[num]++;
    }


}