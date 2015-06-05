package com.example.test4popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-5
 */

public class MyListAdapter extends BaseAdapter {
    String[] mStrings;
    LayoutInflater mInflater;
    Context mContext;

    /**
     * @param mStrings
     */
    public MyListAdapter(Context context, String[] strings) {
        mStrings = strings;
        mContext = context;
        mInflater = LayoutInflater.from(context.getApplicationContext());
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mStrings.length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        return mStrings[position];
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_activity_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.btn = (Button) convertView.findViewById(R.id.btn);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv.setText(mStrings[position]);
        viewHolder.btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupHelper.displayPopupWindow(mContext, mInflater, v);
            }
        });
        return convertView;
    }

   

    class ViewHolder {
        TextView tv;
        Button btn;
    }
}
