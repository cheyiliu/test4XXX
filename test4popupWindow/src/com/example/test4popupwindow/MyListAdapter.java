package com.example.test4popupwindow;

import android.content.Context;
import android.util.Log;
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
     * @param mListView
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
            viewHolder.itemRoot = convertView;
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv.setText(mStrings[position]);
        viewHolder.btn.setOnClickListener(new OnClickListener() {
            ViewHolder holder = viewHolder;

            @Override
            public void onClick(View v) {
                // Rect r = new Rect();
                // mListView.getChildVisibleRect(v, r, null);
                // Log.e("test1", "" + r);
                // Log.e("test1", "" + r.left);
                // Log.e("test1", "" + r.top);
                // Log.e("test1", "" + r.right);
                // Log.e("test1", "" + r.bottom);
                //
                // Rect outRect = new Rect();
                // v.getHitRect(outRect );
                // Log.e("test1", "outRect=" + outRect);
                // Log.e("test1", "" + outRect.left);
                // Log.e("test1", "" + outRect.top);
                // Log.e("test1", "" + outRect.right);
                // Log.e("test1", "" + outRect.bottom);

                Log.e("test1", "" + holder.itemRoot.getTop());
                Log.e("test1", "" + holder.itemRoot.getBottom());

                // TODO work around: 通过计算listview item到底部的距离，当距离小于popup
                // window的高度是，修改popup的背景图(方向箭头向上)，否则展示默认向下的方向箭头图片
                PopupHelper.displayPopupWindow(mContext, mInflater, v);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        Button btn;
        View itemRoot;
    }
}
