package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter;

/**
 * 定位中的标示
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsLoadingMarker extends EsMarkerBase {

    /**
     * @param mapView
     * @param title
     * @param snap
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsLoadingMarker(EsMapView mapView, String title, String snap, int iconId, double lat, double lng) {
        super(mapView, title, snap, iconId, lat, lng);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.didi.es.map.base.EsMarkerBase#getInfoWindowAdapter()
     */
    @Override
    public InfoWindowAdapter getInfoWindowAdapter() {
        return null;
    }

}
