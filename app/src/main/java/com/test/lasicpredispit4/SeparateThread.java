package com.test.lasicpredispit4;

import android.app.ProgressDialog;

public class SeparateThread extends Thread {

    private ProgressDialog progressDialog;
    private boolean hasProgress;

    public SeparateThread(ProgressDialog progressDialog, boolean hasProgress) {
        this.progressDialog = progressDialog;
        this.hasProgress = hasProgress;
    }


    public void run() {
        if (hasProgress) {
            sleepWithProgress();
        }
        progressDialog.dismiss();
    }

    private void sleepWithProgress() {
        try {
            while (progressDialog.getProgress() < 100) {
                Thread.sleep(1000);
                progressDialog.incrementProgressBy(10);
            }
        } catch (InterruptedException e) {
        }
    }
}