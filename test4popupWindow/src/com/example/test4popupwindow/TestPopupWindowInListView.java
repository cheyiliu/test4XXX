package com.example.test4popupwindow;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-5
 */

public class TestPopupWindowInListView extends Activity {
    ListView mListView;
    String[] mStrings = {
            "1", "2", "3", "5", "6", "7", "8", "9", "0", "11", "22", "33", "55", "66", "77", "88", "99", "00", "111", "222", "333", "555",
            "666", "777", "888", "999", "000" };

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list_activity);
        mListView = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new MyListAdapter(this, mStrings);
        mListView.setAdapter(adapter);

        mListView.post(new Runnable() {

            @Override
            public void run() {
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                Log.i("test1", "statusBarHeight=" + statusBarHeight);

                int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
                // statusBarHeight是上面所求的状态栏的高度
                int titleBarHeight = contentTop - statusBarHeight;
                Log.i("test1", "titleBarHeight=" + titleBarHeight);

                DisplayMetrics dm = getResources().getDisplayMetrics();
                // int w_screen = dm.widthPixels;
                int h_screen = dm.heightPixels;
                Log.e("test1", "h_screen=" + h_screen);

            }
        });
    }

}
