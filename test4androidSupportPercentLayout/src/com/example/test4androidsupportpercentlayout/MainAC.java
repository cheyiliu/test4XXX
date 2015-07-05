package com.example.test4androidsupportpercentlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @since 2015-7-5
 */

public class MainAC extends Activity {
    int mLayouts[] = {
            R.layout.percent_frame_layout, R.layout.percent_frame_layout2, R.layout.percent_linear_layout,
            R.layout.percent_relative_layout, R.layout.percent_relative_layout2, R.layout.percent_relative_layout3 };

    Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (mIndex >= mLayouts.length) {
                mIndex = 0;
            }

            setContentView(mLayouts[mIndex]);
            mIndex++;
            mHandler.sendEmptyMessageDelayed(0, 3 * 1000);
            return false;
        }
    });

    int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.sendEmptyMessage(0);
    }

}
