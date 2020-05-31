package com.example.app2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener{
    private static int notificationId = 1;
    private List<NewAlarm> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // Set Onclick Listener.
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);

        loadData();
    }

    @Override
    public void onClick(View v) {
        EditText alarmName = findViewById(R.id.alarmName);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Set notificationId & text.
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", alarmName.getText().toString());

        // getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId()) {
            case R.id.setBtn:
                notificationId ++;

                Integer hour = timePicker.getCurrentHour();
                Integer minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set alarm.
                // set(type, milliseconds, intent)
                alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, AlarmManager.INTERVAL_DAY, alarmIntent);

                Toast.makeText(this, "Zapisano!", Toast.LENGTH_SHORT).show();

                String str_min = "";
                if(minute < 10)
                    str_min+="0";
                str_min += minute.toString();
                String str_hr = "";
                if(hour < 10)
                    str_hr+="0";
                str_hr+=hour.toString();
                items.add(new NewAlarm(notificationId, alarmName.getText().toString(), str_hr + ":" + str_min, true));
                saveData();

                Intent intentLoadNewActivity = new Intent(AlarmActivity.this, AllAlarms.class);
                intentLoadNewActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLoadNewActivity);
                finish();
                break;

            case R.id.cancelBtn:
                finish();
                break;
        }
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
