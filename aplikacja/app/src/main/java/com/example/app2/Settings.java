package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.ArrayList;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    Switch darkModeSwitch;
    Switch pushButton;
    ImageButton mailButton;
    ArrayList<Integer> settingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mailButton = (ImageButton)findViewById(R.id.mail_button);
        darkModeSwitch = (Switch)findViewById(R.id.darkModeSwitch);
        pushButton = (Switch)findViewById(R.id.pushSwitch);
        settingList = new ArrayList<>();
        settingList = SettingsFile.readData(this);

        if(SettingsFile.readData(this).size() ==0){
            settingList.add(0);
            settingList.add(1);
            SettingsFile.writeData(settingList, Settings.this);
        }
        System.out.println(settingList);

        setSwitch();
        setPushNotifications(settingList.get(1) == 1);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Settings.this, Email.class);
                startActivity(intentLoadNewActivity);
            }
        });

        darkModeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (darkModeSwitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Toast.makeText(Settings.this, "Ustawiono tryb ciemny", Toast.LENGTH_SHORT).show();
                    settingList.set(0,1);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Toast.makeText(Settings.this, "Ustawiono tryb jasny", Toast.LENGTH_SHORT).show();
                    settingList.set(0,0);
                }
                System.out.println(settingList);
                SettingsFile.writeData(settingList, Settings.this);
                setPushNotifications(settingList.get(1) == 1);


            }
        });

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pushButton.isChecked()) {
                    settingList.set(1, 1);
                    Toast.makeText(Settings.this, "Włączono powiadomienia push", Toast.LENGTH_SHORT).show();
                }
                else {
                    settingList.set(1, 0);
                    Toast.makeText(Settings.this, "Wyłączono powiadomienia push", Toast.LENGTH_SHORT).show();
                }
                System.out.println(settingList);
                SettingsFile.writeData(settingList, Settings.this);
                setPushNotifications(settingList.get(1) == 1);

            }
        });

    }


    @Override
    public void onClick(View v) {

    }
    private void setSwitch(){
        if(settingList.get(0) == 1)
            darkModeSwitch.toggle();
        if(settingList.get(1) == 1)
            pushButton.toggle();
    }

    private void setPushNotifications(boolean b){
        if(b==false) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("test");
        }
        else
            FirebaseMessaging.getInstance().subscribeToTopic("test");
    }
}
