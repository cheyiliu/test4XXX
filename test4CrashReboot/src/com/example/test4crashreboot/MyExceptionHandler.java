package com.example.test4crashreboot;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.content.Intent;

public class MyExceptionHandler implements
		java.lang.Thread.UncaughtExceptionHandler {
	private final Context myContext;
	private final Class<?> myActivityClass;
	private UncaughtExceptionHandler defaultHandler;

	public MyExceptionHandler(Context context, Class<?> c, UncaughtExceptionHandler defaultHandler) {
		
		this.defaultHandler = defaultHandler;

		myContext = context;   
		myActivityClass = c;
	}

	public void uncaughtException(Thread thread, Throwable exception) {

//		StringWriter stackTrace = new StringWriter();
//		exception.printStackTrace(new PrintWriter(stackTrace));
//		System.err.println(stackTrace);// You can use LogCat too
		Intent intent = new Intent(myContext, myActivityClass);
//		String s = stackTrace.toString();
//		// you can use this String to know what caused the exception and in
//		// which Activity
//		intent.putExtra("uncaughtException",
//				"Exception is: " + stackTrace.toString());
//		intent.putExtra("stacktrace", s);
		myContext.startActivity(intent);
		defaultHandler.uncaughtException(thread, exception);
		// for restarting the Activity
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
}