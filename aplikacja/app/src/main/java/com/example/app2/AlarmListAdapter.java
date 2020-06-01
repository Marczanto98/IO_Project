package com.example.app2;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<NewAlarm> implements AdapterView.OnItemClickListener {
    private List<NewAlarm> items;
    //private List<NewAlarm> itemsFromAllAlarms;
    private Activity context;

    public AlarmListAdapter(Activity context, List<NewAlarm> list){
        super(context, R.layout.alarm_list, list);
        this.context = context;
        this.items = list;
        //loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //View view1 = parent.getChildAt(position);
        //view1.setBackgroundColor(Color.RED);
    }

    static class ViewHolder {
        protected TextView time;
        protected TextView name;
        protected Switch isOn;
        protected  ImageButton deleteBtn;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if(convertView == null){
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.alarm_list, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.time = (TextView)view.findViewById(R.id.time);
            viewHolder.name = (TextView)view.findViewById(R.id.label);
            viewHolder.isOn = (Switch)view.findViewById(R.id.switchon);
            viewHolder.deleteBtn = (ImageButton)view.findViewById(R.id.deleteBtn);

            //TODO
            //fix removing items
            /*viewHolder.deleteBtn.setOnClickListener(this::removeAlarmOnClickHandler);


            //fires every time AllAlarms is revisited
            viewHolder.isOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    NewAlarm element = (NewAlarm)viewHolder.isOn.getTag();

                    element.setSwitch(buttonView.isChecked());

                    //TODO
                    //fix updating switch on change

                    int index = getAlarmPosition(element);
                    items.set(index, element);


                    if(buttonView.isChecked()){
                        String[] time = element.getTime().split(":");
                        Integer hour = Integer.parseInt(time[0]);
                        Integer minute = Integer.parseInt(time[1]);

                        Intent intent = new Intent(context, AlarmReceiver.class);
                        intent.putExtra("notificationId", element.getId());
                        intent.putExtra("todo", element.getName());

                        // getBroadcast(context, requestCode, intent, flags)
                        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, element.getId(),
                                intent, PendingIntent.FLAG_CANCEL_CURRENT);

                        AlarmManager alarm = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

                        // Create time.
                        Calendar startTime = Calendar.getInstance();
                        startTime.set(Calendar.HOUR_OF_DAY, hour);
                        startTime.set(Calendar.MINUTE, minute);
                        startTime.set(Calendar.SECOND, 0);
                        long alarmStartTime = startTime.getTimeInMillis();

                        // Set alarm.
                        // setInexactRepeating(type, milliseconds, interval, intent)
                        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime, AlarmManager.INTERVAL_DAY, alarmIntent);

                    }
                    else{
                        Intent intent = new Intent(context, AlarmReceiver.class);
                        int alarm_id = element.getId();
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm_id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                    }
                }
            });*/

            view.setTag(viewHolder);
            viewHolder.isOn.setTag(items.get(position));
        }
        else {
            view = convertView;
            ((ViewHolder)view.getTag()).isOn.setTag(items.get(position));
        }
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.name.setText(items.get(position).getName());
        holder.time.setText(items.get(position).getTime());
        holder.isOn.setChecked(items.get(position).isSwitchOn());

        //saveData();
        return view;
    }

    private void loadData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("alarm list", null);
        Type type = new TypeToken<ArrayList<NewAlarm>>(){}.getType();
        items/*FromAllAlarms*/ = gson.fromJson(json, type);

        if(items/*FromAllAlarms*/ == null){
            items/*FromAllAlarms*/ = new ArrayList<NewAlarm>();
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items/*FromAllAlarms*/);
        editor.putString("alarm list", json);
        editor.apply();
    }

    private void removeAlarmOnClickHandler(View view){
        //loadData();
        //new AlertDialog.Builder(context).setTitle("touched").show();
        NewAlarm itemToRemove = (NewAlarm)view.getTag();
        //itemsFromAllAlarms.remove(itemToRemove);
        items.remove(itemToRemove);
        Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
        remove(itemToRemove);
        notifyDataSetChanged();
        //saveData();
    }

    private int getAlarmPosition(NewAlarm copy){
        int result = 0;
        for(int i=0; i<items.size(); i++){
            if(items.get(i).getId() == copy.getId())
                result = i;
        }
        return result;
    }

}

