package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;

/**
 * 简单标记类，仅显示为一个小图标，不可点击，无自定义info window。 用法： 添加到地图， EsSimpleMarker esSimpleMarker = new
 * EsSimpleMarker(mapView, R.drawable.red_location, 39.905029,116.389546);
 * 从地图删除，esSimpleMarker.removeFromMap();
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsSimpleMarker extends EsMarkerBase {

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsSimpleMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mMapView.getEventHub().unregisterOnMarkerClickListener(mMarker, this);
        mMapView.getEventHub().unregisterOnInfoWindowClickListener(mMarker, this);
        setClickable(false);
    }
}
