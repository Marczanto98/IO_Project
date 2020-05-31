package com.example.app2;

import android.os.AsyncTask;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class Obliczenia extends AsyncTask<Void, Void, Void> {

    @Override
    public Void doInBackground(Void... voids) {
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("testtttttttttttttttt");
        return null;
    }
}