package biz;

import java.math.BigDecimal;

import android.os.Bundle;
import android.util.Log;

public class CmdSubtract extends iCmd {
	private static final String TAG = "test1";
	private BigDecimal a;
	private BigDecimal b;

	public CmdSubtract(BigDecimal a, BigDecimal b) {
		this.a = a;
		this.b = b;
		no = counter++;
	}

	@Override
	public Bundle exe() throws Exception {
//		Log.i(TAG, this + ", exe, tid=" + Thread.currentThread().getId());
		Thread.sleep(500);// demo for long operation
		Bundle bundle = new Bundle();
		bundle.putString("result", System.currentTimeMillis()+"");
		// throw new Exception("");// for test
		Log.i(TAG, no + ", CmdSubtract exe, tid=" + Thread.currentThread().getId());
		return bundle;
	}

}
