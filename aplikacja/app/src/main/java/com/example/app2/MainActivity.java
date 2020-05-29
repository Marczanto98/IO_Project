package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    ImageButton newsButton;
    ImageButton contactButton;
    ImageButton adviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = (ImageButton)findViewById(R.id.shopping_list);
        contactButton = (ImageButton)findViewById(R.id.contactButton);
//      newsButton = (ImageButton)findViewById(R.id.news_btn);
        adviceButton = (ImageButton)findViewById(R.id.advice_button);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, ListActivity.class);
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

        adviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Advice.class);
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

                    // TODO
                    // Sprawdzanie czy selectedFragment to ta sama klasa co aktualnie
                    // wyświetlany na ekranie fragment. Jeśli tak, to nie robimy poniższego commita.
                    // Zapobiega to np. ponownemu ładowaniu statystyk gdy klikniemy przycisk
                    // "Przypadki" znajdując się aktualnie w sekcji "Przypadki".

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}
