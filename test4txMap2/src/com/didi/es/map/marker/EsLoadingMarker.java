package com.didi.es.map.marker;

import android.view.LayoutInflater;
import android.view.View;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.example.test4txmap.R;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 定位中的标示
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsLoadingMarker extends EsMarkerBase {

    private View mLoadingView;

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsLoadingMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mLoadingView = LayoutInflater.from(EsApp.sAppContext).inflate(R.layout.map_marker_loading_popup, null);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return mLoadingView;
    }

}
