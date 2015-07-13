package com.example.takeorselectpicandencodetostring;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    private static Context sContext;

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
