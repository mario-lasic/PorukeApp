package com.test.lasicpredispit4;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

    TextView tvSender, tvDolazna;
    String b, p;
    View view;
    Context _context;
    PendingIntent pi;
    BroadcastReceiver br2;
    AlarmManager am;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment3, container, false);
        setup();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidgets();
    }

    private void initWidgets() {
        tvDolazna = (TextView)getView().findViewById(R.id.tvDolazna);
        tvSender = (TextView)getView().findViewById(R.id.tvSender);
    }

    private void setup() {
        {
            br2 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent2) {
                    Log.i("frag3", "Poruka je primljena u 3 fragment");
                    String b = intent2.getStringExtra("broj");
                    String p = intent2.getStringExtra("poruka");
                    tvDolazna.setText(p);
                    tvSender.setText(b);
                }
            };
            _context.registerReceiver(br2, new IntentFilter("dolazna_poruka"));
            pi = PendingIntent.getBroadcast(_context, 0, new Intent("dolazna_poruka"), 0);
            am = (AlarmManager) (_context.getSystemService(Context.ALARM_SERVICE));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
        _context.registerReceiver(br2,new IntentFilter("dolazna_poruka"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _context.unregisterReceiver(br2);
    }
}

