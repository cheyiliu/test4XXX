package com.example.test4leakcannary;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

	public static RefWatcher getRefWatcher(Context context) {
		MyApp application = (MyApp) context
				.getApplicationContext();
		return application.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();
		refWatcher = LeakCanary.install(this);
	}

}
