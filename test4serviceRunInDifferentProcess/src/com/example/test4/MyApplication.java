package com.example.test4;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("test", "pid=" + android.os.Process.myPid() + ", onCreate, "
				+ this);
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		Log.e("test", "pid=" + android.os.Process.myPid() + ", onTerminate, "
				+ this);
		super.onTerminate();
	}

}
