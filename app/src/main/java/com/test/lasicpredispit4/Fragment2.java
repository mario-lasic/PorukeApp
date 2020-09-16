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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    TextView tvBroj, tvPoruka;
    String b, p;
    View view;
    Context _context;
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment2, container, false);
        setup();
        return v;
    }

    private void setup() {
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String b = intent.getStringExtra("broj");
                String p = intent.getStringExtra("poruka");
                tvBroj.setText(b);
                tvPoruka.setText(p);
            }
        };
        _context.registerReceiver(br, new IntentFilter("posalji_podatke"));
        pi = PendingIntent.getBroadcast(_context, 0, new Intent("posalji_podatke"), 0);
        am = (AlarmManager)(_context.getSystemService(Context.ALARM_SERVICE));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initWidgets();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _context.unregisterReceiver(br);
    }

    private void initWidgets() {
        tvBroj = (TextView)getView().findViewById(R.id.tvBroj);
        tvPoruka = (TextView)getView().findViewById(R.id.tvPoruka);
    }

}
