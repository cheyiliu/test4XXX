package com.didi.es.map.marker;

import android.view.LayoutInflater;
import android.view.View;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.example.test4txmap.R;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 在地图上展示行驶里程时间和价格信息，新增设置里程时间和价格的api。
 * 
 * @author houshengyong
 * @since 2015-6-4
 */

public class EsMileTimeAndCostMarker extends EsMarkerBase {
    private View mRootView;

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsMileTimeAndCostMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mRootView = LayoutInflater.from(EsApp.sAppContext).inflate(R.layout.map_marker_mile_time_cost, null);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return mRootView;
    }

    /**
     * 设置里程时间和价格
     * 
     * @param mile
     * @param time
     * @param cost
     */
    public void setMileTimeCost(String mile, String time, String cost) {
    }

}
