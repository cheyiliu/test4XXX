package com.example.test4localbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
本地广播的接收器在manifest里注册的话，收不到消息的。
 */
public class LocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive, intent=" + intent, Toast.LENGTH_LONG).show();
    }

}
