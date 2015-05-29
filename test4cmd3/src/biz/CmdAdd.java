package biz;

import java.math.BigInteger;

import android.os.Bundle;
import android.util.Log;

public class CmdAdd extends iCmd {
	private static final String TAG = "test1";
	private BigInteger a;
	private BigInteger b;

	public CmdAdd(BigInteger a, BigInteger b) {
		this.a = a;
		this.b = b;
		no = counter++;
	}

	@Override
	public Bundle exe() throws InterruptedException {
		
		Thread.sleep(300);// demo for long operation
		Bundle bundle = new Bundle();
		bundle.putString("result", System.currentTimeMillis()+"");
		Log.i(TAG, no + ", CmdAdd exe, tid=" + Thread.currentThread().getId());
		return bundle;
	}

}
