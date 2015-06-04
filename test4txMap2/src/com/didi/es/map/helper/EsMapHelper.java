package com.didi.es.map.helper;

import java.util.List;

import android.location.Location;

import com.didi.es.map.base.EsMapView;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds.Builder;

/**
 * 地图工具类，包括缩放等
 * 
 * @author houshengyong
 * @since 2015-5-27
 */
public class EsMapHelper {
    private EsMapHelper() {
    }

    /**
     * 将一点移动到地图中心并缩放
     * 
     * @param mapView
     * @param lat
     * @param lng
     * @param zoom
     */
    public static void setCenterAndZoom(EsMapView mapView, double lat, double lng, float zoom) {
        if (null != mapView) {
            CameraUpdate cu = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(new LatLng(lat, lng)).zoom(zoom).build());
            mapView.getMap().animateCamera(cu);
        }
    }

    /**
     * 将一点移动到地图中心
     * 
     * @param mapView
     * @param lat
     * @param lng
     */
    public static void setCenter(EsMapView mapView, double lat, double lng) {
        if (null != mapView) {
            CameraUpdate cu = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(lat, lng)).build());
            mapView.getMap().animateCamera(cu);
        }
    }

    /**
     * 缩放地图，使所有传入的经纬度点都能显示下
     * 
     * @param mapView
     * @param latLngs
     * @param left
     * @param right
     * @param top
     * @param bottom
     */
    public static void zoomToSpan(EsMapView mapView, List<LatLng> latLngs, int left, int right, int top, int bottom) {
        if (null != mapView && null != latLngs && latLngs.size() > 0) {
            Builder boundbuilder = new LatLngBounds.Builder();
            for (int i = latLngs.size() - 1; i >= 0; i--) {
                boundbuilder.include(latLngs.get(i));
            }
            LatLngBounds bounds = boundbuilder.build();

            mapView.getMap().animateCamera(CameraUpdateFactory.newLatLngBoundsRect(bounds, left, right, top, bottom));
        }
    }

    /**
     * 增量缩放地图
     * 
     * @param mapView
     * @param delat
     */
    public static void zoomBy(EsMapView mapView, float delat) {
        if (null != mapView) {
            mapView.getMap().animateCamera(CameraUpdateFactory.zoomBy(delat));
        }
    }

    /**
     * 缩放地图到某级别
     * 
     * @param mapView
     * @param level
     */
    public static void zoomTo(EsMapView mapView, float level) {
        if (null != mapView) {
            mapView.getMap().animateCamera(CameraUpdateFactory.zoomTo(level));
        }
    }

    /**
     * 计算两坐标点之间的直线距离，单位为米
     * 
     * @param from
     * @param to
     * @return the distance
     */
    public static float distanceBetween(LatLng from, LatLng to) {
        if (from == null || to == null)
            return 0;
        float[] results = new float[5];
        Location.distanceBetween(from.latitude, from.longitude, to.latitude, to.longitude, results);
        return results[0];
    }

    /**
     * 设置logo的停靠位置
     * 
     * @param mapView
     * @param anchor
     *            : 0左下；1右下；2右上；3左上
     */
    public static void setLogoAnchor(EsMapView mapView, int anchor) {
        if (null != mapView) {
            if (0 == anchor || 1 == anchor || 2 == anchor || 3 == anchor) {
                mapView.getMap().setLogoAnchor(anchor);
            }
        }
    }

    /**
     * 设置logo是否可见
     * 
     * @param mapView
     * @param visible
     */
    public static void setMapLogoVisible(EsMapView mapView, boolean visible) {
        if (null != mapView) {
            mapView.getMap().setLogoVisible(visible);
        }
    }

}
