package com.test.lasicpredispit4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

public class MyReciver extends BroadcastReceiver {

    String broj = "Prazno", poruka = "Prazno";


    @Override
    public void onReceive(Context context, Intent intent) {
        if ("poslana_poruka".equals(intent.getAction())) {
            poruka = intent.getStringExtra("poruka");
            broj = intent.getStringExtra("broj");
            Toast.makeText(context, poruka, Toast.LENGTH_LONG).show();
        }


    }
}



