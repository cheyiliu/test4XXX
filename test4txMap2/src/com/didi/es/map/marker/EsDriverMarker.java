package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;

/**
 * 司机位置标记
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsDriverMarker extends EsMarkerBase {

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsDriverMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
    }

}
