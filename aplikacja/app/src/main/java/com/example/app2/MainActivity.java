package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
//    private static int SPLASH_TIME_OUT = 4000;

    ImageButton imageButton;
    ImageButton newsButton;
    ImageButton contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(homeIntent);
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

        imageButton = (ImageButton)findViewById(R.id.shopping_list);
        contactButton = (ImageButton)findViewById(R.id.contactButton);
//        newsButton = (ImageButton)findViewById(R.id.news_btn);
//
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, List.class);
                startActivity(intentLoadNewActivity);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Contact.class);
                startActivity(intentLoadNewActivity);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navbar_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navbar_cases:
                            selectedFragment = new CasesFragment();
                            break;
                        case R.id.navbar_news:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.navbar_symptoms:
                            selectedFragment = new SymptomsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}
