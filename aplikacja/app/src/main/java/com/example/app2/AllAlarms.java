package com.example.app2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AllAlarms extends AppCompatActivity {
    private Button newAlarm;
    private ListView alarmList;
    private ArrayAdapter<NewAlarm> adapter;
    private ArrayList<NewAlarm> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_alarms);

        newAlarm = (Button)findViewById(R.id.new_alarm);

        newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(AllAlarms.this, AlarmActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        loadData();
        adapter = new AlarmListAdapter(this, items);
        alarmList = findViewById(R.id.alarms_list);
        alarmList.setAdapter(adapter);

        alarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                //Cancel alarm - jeszcze nie działa
                NewAlarm itemToRemove = items.get(position);
                Intent intent = new Intent(AllAlarms.this, AlarmReceiver.class);
                int alarm_id = itemToRemove.getId();
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AllAlarms.this, alarm_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);

                //remove from listview
                items.remove(position);
                saveData();
                adapter.notifyDataSetChanged();
                Toast.makeText(AllAlarms.this, "Usunięto", Toast.LENGTH_SHORT).show();
            }
        });

        //saveData();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("alarm list", null);
        Type type = new TypeToken<ArrayList<NewAlarm>>(){}.getType();
        items = gson.fromJson(json, type);

        if(items == null){
            items = new ArrayList<NewAlarm>();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("alarm list", json);
        editor.apply();
    }
}
