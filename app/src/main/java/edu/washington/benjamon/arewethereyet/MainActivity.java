package edu.washington.benjamon.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    PendingIntent alarmIntent = null;
    BroadcastReceiver alarmReceiver = new MyReceiver();
    AlarmManager am;
    EditText message;
    EditText number;
    EditText frequency;
    int freqNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(alarmReceiver, new IntentFilter("edu.washington.benjamon.arewethereyet.MyReceiver"));


        message = (EditText) findViewById(R.id.messagefield);
        number = (EditText) findViewById(R.id.numberfield);
        frequency = (EditText) findViewById(R.id.frequencyField);
        final Button startButt = (Button) findViewById(R.id.startbuton);
        startButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startButt.getText().toString().equals("START")) {
                    boolean isint = true;
                    try {
                        freqNum = Integer.parseInt(frequency.getText().toString());
                    } catch (NumberFormatException e) {
                        isint = false;
                    }
                    if (isint && !message.getText().toString().equals("") && !number.getText().toString().equals("")) {
                        startButt.setText((CharSequence) "STOP");

                        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        Intent i = new Intent();
                        i.setAction("edu.washington.benjamon.arewethereyet.MyReceiver");
                        i.putExtra("message", message.getText().toString());
                        i.putExtra("number", number.getText().toString());
                        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
                        am.setRepeating(AlarmManager.RTC, System.currentTimeMillis() + 1000, 60000 * freqNum, alarmIntent);
                    }
                } else {
                    startButt.setText("START");
                    am.cancel(alarmIntent);
                    alarmIntent.cancel();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        am.cancel(alarmIntent);
        alarmIntent.cancel();
    }
}
