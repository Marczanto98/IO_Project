package com.example.app2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Contact extends AppCompatActivity implements View.OnClickListener
{
    ImageButton callNFZ;
    ImageButton callFromAbroad;
    public String number;
    ImageButton back;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.contact);

        callNFZ = findViewById(R.id.callNFZ);
        callFromAbroad = findViewById(R.id.callFromAbroad);
        back = findViewById(R.id.backToMain);

        callNFZ.setOnClickListener(this);
        callFromAbroad.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void call(View v, String number)
    {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number)));
        finish();
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        switch (id)
        {
            case R.id.callNFZ:
                number = "800190590";
                call(v, number);
                break;
            case R.id.callFromAbroad:
                number = "221256600";
                call(v, number);
                break;
        }

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

