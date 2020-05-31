package com.example.app2;


import java.io.Serializable;

public class NewAlarm implements Serializable {
    private int notificationId;
    private String name;
    private String time;
    private boolean switchOn;

    public NewAlarm(int notificationId, String name, String time, boolean switchOn){
        this.notificationId = notificationId;
        this.name = name;
        this.time = time;
        this.switchOn = switchOn;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getTime(){return time;}
    public void setTime(String time){this.time = time;}
    public boolean isSwitchOn(){return switchOn;}
    public void setSwitch(boolean switchOn){this.switchOn = switchOn;}
    public int getId(){return notificationId;}

}
