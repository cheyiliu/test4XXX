package com.example.test4listview;

import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Address;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

            vi = mInflater.inflate(R.layout.search_result_view_list_item_complex, null);
//        TextView tvTextView = (TextView) vi.findViewById(R.id.search_result_listview_item_textview);
//        tvTextView.setText("name = ..." + mResultList.get(position).getCountryName());
           
            final View menu = vi.findViewById(R.id.expandable);
            View buttonView =  vi.findViewById(R.id.expandable_toggle_button);
            buttonView.setOnClickListener(new OnClickListener() {
                
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onClick(View v) {
                    menu.animate()
                    .translationY(0)
                    .alpha(0.0f)
                    .setDuration(5000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            menu.setVisibility(View.GONE);
                        }
                    });
                }
            });
        // TODO holder
        return vi;
    }

}
