package biz;

import android.util.Log;

public class MyCmd implements iCmd{

	private static final String TAG = "test";

	@Override
	public boolean exe() {
		// TODO Auto-generated method stub
		Log.e(TAG, "MyCmd exe, thread id=" + Thread.currentThread().getId());
		return false;
	}

}
