package com.example.test4crashreboot;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this,
        		Activity.class, Thread.getDefaultUncaughtExceptionHandler()));
        
        findViewById(R.id.btn_crash).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Object object = new Object();
//				object = null;
//				object.getClass();
				 Log.i("test", getTag());
			}
		});
        
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private static String getTag() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = elements[0];
        String traceClassName = null;
        for (StackTraceElement element : elements) {
            traceClassName = element.getClassName();
            if (traceClassName.startsWith("MainActivity")
                    || traceClassName.startsWith(Thread.class.getCanonicalName()) || traceClassName.startsWith("dalvik.system.VMStack"))
                continue;
            else {
                stackTraceElement = element;
                break;
            }
        }
        String invokedClass = stackTraceElement.getClassName();
        invokedClass = invokedClass.substring(invokedClass.lastIndexOf(".") + 1);
        int lineNumber = stackTraceElement.getLineNumber();
        String tag = invokedClass + ":" + lineNumber;
        return tag;
    }
}
