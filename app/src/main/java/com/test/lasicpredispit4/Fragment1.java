package com.test.lasicpredispit4;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    EditText etPoruka, etBroj;
    Button btnSMS;
    private ProgressDialog progressDialog;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment1, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initWidgets();
        setupListeners();

        super.onViewCreated(view, savedInstanceState);
    }


    private void setupListeners() {
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poruka = etPoruka.getText().toString();
                Intent intent = new Intent();
                intent.setAction("posalji_podatke");
                intent.putExtra("broj", etBroj.getText().toString());
                intent.putExtra("poruka", poruka);
                getContext().sendBroadcast(intent);

                final Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("smsto:"));
                i.putExtra("address", etBroj.getText().toString());
                i.putExtra("sms_body", etPoruka.getText().toString());

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Čekanje");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                SeparateThread sp = new SeparateThread(progressDialog, true);
                sp.start();
                progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        startActivity(i);
                    }
                });
            }
        });
    }

    private void initWidgets() {
        etPoruka = (EditText) getView().findViewById(R.id.etPoruka);
        etBroj = (EditText)getView().findViewById(R.id.etBroj);
        btnSMS = (Button)getView().findViewById(R.id.btnSMS);
    }
}
