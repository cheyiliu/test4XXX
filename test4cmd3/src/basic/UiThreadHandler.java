package basic;

import android.os.Handler;
import android.os.Looper;

public class UiThreadHandler {
	private static Handler uiHandler = new Handler(Looper.getMainLooper());

	private static Object token = new Object();

	public final static boolean post(Runnable r) {
		if (uiHandler == null)
			return false;
		return uiHandler.post(r);
	}

	public final static boolean postDelayed(Runnable r, long delayMillis) {
		if (uiHandler == null)
			return false;

		return uiHandler.postDelayed(r, delayMillis);
	}

	public final static Handler getUiHandler() {
		return uiHandler;
	}

	public final static boolean postOnceDelayed(Runnable r, long delayMillis) {
		if (uiHandler == null)
			return false;
		uiHandler.removeCallbacks(r, token);
		return uiHandler.postDelayed(r, delayMillis);
	}

	public static void removeCallbacks(Runnable runnable) {
		if (uiHandler == null)
			return;
		uiHandler.removeCallbacks(runnable);
	}
}
