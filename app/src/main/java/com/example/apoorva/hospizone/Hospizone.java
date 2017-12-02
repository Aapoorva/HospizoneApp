package com.example.apoorva.hospizone;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Apoorva on 16-Aug-17.
 */

public class Hospizone extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
