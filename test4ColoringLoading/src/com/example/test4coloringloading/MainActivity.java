package com.example.test4coloringloading;

import jp.co.recruit_lifestyle.android.widget.ColoringLoadingView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        final ColoringLoadingView loadingView = (ColoringLoadingView) findViewById(R.id.main_loading);
        loadingView.setCharacter(ColoringLoadingView.Character.BUTTERFLY);
        loadingView.setColoringColor(0xFFFF1744);

        findViewById(R.id.main_start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.setVisibility(View.VISIBLE);
                loadingView.startDrawAnimation();
            }
        });
    }
}
