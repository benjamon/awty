package edu.washington.benjamon.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,  intent.getStringExtra("number") + ": " + intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
    }
}
