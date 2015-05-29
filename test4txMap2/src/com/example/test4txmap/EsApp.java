package com.example.test4txmap;

import android.app.Application;
import android.content.Context;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-5-27
 */

public class EsApp extends Application {

    public static Context sAppContext;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }

}
