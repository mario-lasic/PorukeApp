package com.test.lasicpredispit4;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.test.lasicpredispit4.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    MyReciver receiver = new MyReciver();
    Button btnExit;
    String s, p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();

        IntentFilter filter = new IntentFilter();
        filter.addAction("poslana_poruka");
        registerReceiver(receiver, filter);



        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        btnExit = (Button)findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitDialog ed = new ExitDialog();
                ed.show(getSupportFragmentManager(), "Exit Dialog");
            }
        });

    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

}
