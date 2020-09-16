package com.test.lasicpredispit4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {


    final SmsManager sms = SmsManager.getDefault();
    String broj, poruka;
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    broj = phoneNumber;
                    poruka = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "Broj: "+ broj + "; poruka: " + poruka);
                    Toast.makeText(context, "Broj: "+ broj + ", Poruka: " + poruka, Toast.LENGTH_LONG).show();

                    Intent intent2 = new Intent("dolazna_poruka");
                    intent2.putExtra("poruka", poruka);
                    intent2.putExtra("broj", broj);
                    context.sendBroadcast(intent2);


                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }


    }
}