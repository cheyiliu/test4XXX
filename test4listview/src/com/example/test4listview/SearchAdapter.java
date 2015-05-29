package com.example.test4listview;

import java.util.List;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-5-29
 */

public class SearchAdapter extends BaseAdapter {

    List<Address> mResultList;
    Context mContext;
    LayoutInflater mInflater;

    public SearchAdapter(List<Address> resultList, Context context) {
        mResultList = resultList;
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mResultList.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        return mResultList.get(position);
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
        View vi = convertView;
//        if (convertView == null)

            vi = mInflater.inflate(R.layout.search_result_view_list_item, null);
        TextView tvTextView = (TextView) vi.findViewById(R.id.search_result_listview_item_textview);
        tvTextView.setText("name = ..." + mResultList.get(position).getCountryName());

        // TODO holder
        return vi;
    }

}
