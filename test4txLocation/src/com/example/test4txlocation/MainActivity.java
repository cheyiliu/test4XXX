package com.example.test4txlocation;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity implements TencentLocationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TencentLocationRequest request = TencentLocationRequest.create();
		TencentLocationManager locationManager = TencentLocationManager
				.getInstance(this);
		int error = locationManager.requestLocationUpdates(request, this);
		Log.i("test",
				"MainActivity, onCreate, locationManager.requestLocationUpdates returns "
						+ error);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(TencentLocation location, int error,
			String reason) {
		Log.i("test", "MainActivity, onLocationChanged, " + location);
		Log.i("test", "MainActivity, onLocationChanged, " + error);
		Log.i("test", "MainActivity, onLocationChanged, " + reason);
		if (TencentLocation.ERROR_OK == error) {

		} else {
			// 定位失败
		}
	}

	@Override
	public void onStatusUpdate(String arg0, int arg1, String arg2) {
		Log.i("test", "MainActivity, onStatusUpdate, " + arg0);
		Log.i("test", "MainActivity, onStatusUpdate, " + arg1);
		Log.i("test", "MainActivity, onStatusUpdate, " + arg2);
	}

	@Override
	protected void onDestroy() {
		TencentLocationManager locationManager = TencentLocationManager
				.getInstance(this);
		locationManager.removeUpdates(this);
		super.onDestroy();
	}

}
