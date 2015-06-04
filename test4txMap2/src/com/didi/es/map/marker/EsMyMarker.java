package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;

/**
 * '我'的当前位置标示
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsMyMarker extends EsMarkerBase {

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsMyMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
    }}
