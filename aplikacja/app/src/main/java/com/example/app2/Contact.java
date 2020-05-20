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

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.contact);

        callNFZ = findViewById(R.id.callNFZ);
        callFromAbroad = findViewById(R.id.callFromAbroad);

        callNFZ.setOnClickListener(this);
        callFromAbroad.setOnClickListener(this);
    }

    public void call(View v, String number)
    {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number)));
        finish();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
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
}

