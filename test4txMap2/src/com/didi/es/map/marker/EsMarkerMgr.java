package com.didi.es.map.marker;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;

/**
 * 跟业务相关的辅助类
 * 
 * @author houshengyong
 * @since 2015-6-4
 */

public class EsMarkerMgr {
    private EsMarkerMgr() {
    }

    private static EsMarkerBase sLoadingMarker;

    /**
     * 在地图上展示加载标记
     * 
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public static void showLoadingMarker(EsMapView mapView, int iconId, double lat, double lng) {
        sLoadingMarker = new EsSingleLineInfoMarker(mapView, iconId, lat, lng);
    }

    /**
     * 从地图上移除加载标记
     */
    public static void hideLoadingMarker() {
        if (null != sLoadingMarker) {
            sLoadingMarker.removeFromMap();
            sLoadingMarker = null;
        }
    }

    public static void showMyPosMarker(EsMapView mapView, int iconId, double lat, double lng) {
    }

    public static void updateMyPosMarker(double lat, double lng) {
    }

    public static void hideMyPosMarker() {
    }

    public static void showDriverPosMarker(EsMapView mapView, int iconId, double lat, double lng) {
    }

    public static void updateDriverPosMarker(double lat, double lng) {
    }

    public static void hideDriverPosMarker() {
    }

    // others
}
