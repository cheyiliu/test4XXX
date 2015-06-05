package com.example.test4popupwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnClickListener l = new OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupHelper.displayPopupWindow(MainActivity.this, LayoutInflater.from(MainActivity.this), v);
            }
        };

        findViewById(R.id.btn_1).setOnClickListener(l);
        findViewById(R.id.btn_2).setOnClickListener(l);

        findViewById(R.id.btn_3).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestPopupWindowInListView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
