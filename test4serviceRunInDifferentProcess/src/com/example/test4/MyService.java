package com.example.test4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("test", "pid=" + android.os.Process.myPid() + ", onCreate, "
				+ this);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("test", "pid=" + android.os.Process.myPid() + ", onDestroy, "
				+ this);
		
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
