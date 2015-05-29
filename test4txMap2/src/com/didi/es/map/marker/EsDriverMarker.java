package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter;

/**
 * 司机位置标记
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsDriverMarker extends EsMarkerBase {

    /**
     * @param mapView
     * @param title
     * @param snap
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsDriverMarker(EsMapView mapView, String title, String snap, int iconId, double lat, double lng) {
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
