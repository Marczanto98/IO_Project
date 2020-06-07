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
    private Activity context;

    public AlarmListAdapter(Activity context, List<NewAlarm> list){
        super(context, R.layout.alarm_list, list);
        this.context = context;
        this.items = list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    static class ViewHolder {
        protected TextView time;
        protected TextView name;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if(convertView == null){
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.alarm_list, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.time = (TextView)view.findViewById(R.id.time);
            viewHolder.name = (TextView)view.findViewById(R.id.label);

            view.setTag(viewHolder);
        }
        else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.name.setText(items.get(position).getName());
        holder.time.setText(items.get(position).getTime());

        return view;
    }
}

