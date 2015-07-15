package com.example.test4localbroadcast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
        localBroadcastManager.registerReceiver(new LocalReceiver(), new IntentFilter("a.b.c.action"));

        findViewById(R.id.tv_hello).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "send broadcast...", Toast.LENGTH_LONG).show();
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this);
                localBroadcastManager.sendBroadcast(new Intent("a.b.c.action"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
