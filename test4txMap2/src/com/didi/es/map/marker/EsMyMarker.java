package com.didi.es.map.marker;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * '我'的当前位置标示
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsMyMarker extends EsMarkerBase {

    protected static final String TAG = EsMyMarker.class.getSimpleName();
    int mIconId;

    /**
     * @param mapView
     * @param title
     * @param snap
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsMyMarker(EsMapView mapView, String title, String snap, int iconId, double lat, double lng) {
        super(mapView, title, snap, iconId, lat, lng);
        this.mIconId = iconId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.es.map.base.EsMarkerBase#getInfoWindowAdapter()
     */
    @Override
    public InfoWindowAdapter getInfoWindowAdapter() {
        //TODO 将InfoWindowAdapter作用范围提升为成员变量
        Log.i(TAG, "EsMyMarker, getInfoWindowAdapter");
        return new InfoWindowAdapter() {

            @Override
            public View getInfoWindowPressState(Marker arg0) {
                Log.i(TAG, "EsMyMarker, getInfoWindowPressState, Marker=" + arg0.getId());
                ImageView imageView = new ImageView(EsApp.sAppContext);
                imageView.setImageResource(mIconId);
                return imageView;
            }

            @Override
            public View getInfoWindow(Marker arg0) {
                Log.i(TAG, "EsMyMarker, getInfoWindow, Marker=" + arg0.getId());
                ImageView imageView = new ImageView(EsApp.sAppContext);
                imageView.setImageResource(mIconId);
                return imageView;
            }

            @Override
            public View getInfoContents(Marker arg0) {// TODO
                Log.i(TAG, "EsMyMarker, getInfoContents, Marker=" + arg0.getId());
                ImageView imageView = new ImageView(EsApp.sAppContext);
                imageView.setImageResource(mIconId);
                return imageView;
            }
        };

    }

}
