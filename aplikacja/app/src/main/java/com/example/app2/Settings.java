package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    Switch darkModeSwitch;
    Switch pushButton;
    ImageButton mailButton;
    ImageButton rateUsButton;
    Button importButton;
    Button exportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mailButton = (ImageButton)findViewById(R.id.mail_button);
        darkModeSwitch = (Switch)findViewById(R.id.darkModeSwitch);
        pushButton = (Switch)findViewById(R.id.pushSwitch);
        importButton = (Button)findViewById(R.id.importButton);
        exportButton = (Button)findViewById(R.id.exportButton);

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
                if (darkModeSwitch.isChecked())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
