package com.didi.es.map.marker;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.example.test4txmap.R;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 在地图上展示行驶里程数和所用时间。
 * 
 * @author houshengyong
 * @since 2015-6-4
 */

public class EsFromToInfoMarker extends EsMarkerBase {
    private View mRootView;
    private TextView mTextViewFrom;
    private TextView mTextViewTo;

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsFromToInfoMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mRootView = LayoutInflater.from(EsApp.sAppContext).inflate(R.layout.map_marker_from_to, null);
        mTextViewFrom = (TextView) mRootView.findViewById(R.id.from_pos);
        mTextViewTo = (TextView) mRootView.findViewById(R.id.to_pos);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return mRootView;
    }

    /**
     * 设置从哪里出发的相关信息
     * 
     * @param fromPos
     * @param fromPosDes
     */
    public void setFromInfo(String fromPos, String fromPosDes) {
        mTextViewFrom.setText(fromPos);
        showInfoWindow();
    }

    /**
     * 设置到哪里去的相关信息
     * 
     * @param toPos
     */
    public void setToInfo(String toPos) {
        mTextViewTo.setText(toPos);
        showInfoWindow();
    }

    /**
     * 设置从哪里到哪里
     * 
     * @param fromPos
     * @param toPos
     */
    public void setFromToInfo(String fromPos, String toPos) {
        mTextViewFrom.setText(fromPos);
        mTextViewTo.setText(toPos);
        showInfoWindow();
    }
}
