package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AllAlarms extends AppCompatActivity {
    private Button newAlarm;
    private ImageButton backButton;
    private ListView alarmList;
    private ArrayAdapter<NewAlarm> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_alarms);

        newAlarm = (Button)findViewById(R.id.new_alarm);
        backButton = findViewById(R.id.back);

        newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(AllAlarms.this, AlarmActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //List<NewAlarm> items;
        //items = AlarmFileHelper.readData(this);

        NewAlarm test1 = new NewAlarm("pobudka", "15:00", true);
        NewAlarm test2 = new NewAlarm("pobudka", "16:00", false);
        List<NewAlarm> items = new ArrayList<NewAlarm>();
        items.add(test1);
        items.add(test2);
        adapter = new AlarmListAdapter(this, items);
        alarmList = findViewById(R.id.alarms_list);
        alarmList.setAdapter(adapter);
    }
}
