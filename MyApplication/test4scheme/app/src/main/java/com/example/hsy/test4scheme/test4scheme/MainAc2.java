package com.example.hsy.test4scheme.test4scheme;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

/**
 * Created by hsy on 2015/8/10.
 */
public class MainAc2 extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this, "addfadfadf", Toast.LENGTH_LONG).show();
    }
}
