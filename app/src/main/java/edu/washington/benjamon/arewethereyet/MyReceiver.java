package edu.washington.benjamon.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("" + intent.getStringExtra("number"), null, "" + intent.getStringExtra("message"), null, null);
    }
}
