package com.example.app2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<NewAlarm> {
    private List<NewAlarm> list;
    private Activity context;

    public AlarmListAdapter(Activity context, List<NewAlarm> list){
        super(context, R.layout.alarm_list, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView time;
        protected TextView name;
        protected Switch isOn;
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

            view.setTag(viewHolder);
            viewHolder.isOn.setTag(list.get(position));
        }
        else {
            view = convertView;
            ((ViewHolder)view.getTag()).isOn.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.name.setText(list.get(position).getName());
        holder.time.setText(list.get(position).getTime());
        holder.isOn.setChecked(list.get(position).isSwitchOn());
        return view;
    }
}
