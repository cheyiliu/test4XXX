package com.example.test4popupwindow;

import android.app.Activity;
import android.os.Bundle;
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
        setContentView(R.layout.list_activity);
        mListView = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new MyListAdapter(this, mStrings);
        mListView.setAdapter(adapter);
    }

}
