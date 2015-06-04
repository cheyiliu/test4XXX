package com.didi.es.map.marker;

import android.view.LayoutInflater;
import android.view.View;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.example.test4txmap.R;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 在地图上展示行驶里程数和所用时间，新增设置里程和时间的api。
 * 
 * @author houshengyong
 * @since 2015-6-4
 */

public class EsMileTimeMarker extends EsMarkerBase {
    private View mRootView;

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsMileTimeMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mRootView = LayoutInflater.from(EsApp.sAppContext).inflate(R.layout.map_marker_mile_time, null);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return mRootView;
    }

    /**
     * 设置里程和时间
     * 
     * @param mile
     * @param time
     */
    public void setMileTime(String mile, String time) {

    }
}
