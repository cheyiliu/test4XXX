package com.didi.es.map.base;

import android.util.Log;
import android.view.View;

import com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

/**
 * 标记类基类，提供默认的事件并建立和info window的关联
 * 
 * @author houshengyong
 * @since 2015-5-25
 */

public class EsMarkerBase implements OnMarkerClickListener, OnInfoWindowClickListener, InfoWindowAdapter {
    private static final String TAG = EsMarkerBase.class.getSimpleName();

    protected Marker mMarker;
    protected EsMapView mMapView;

    /**
     * 构造一个标记并添加到地图上，并默认注册了标记点击事件
     * 
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsMarkerBase(EsMapView mapView, int iconId, double lat, double lng) {
        mMapView = mapView;

        MarkerOptions markeroptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(iconId)).position(new LatLng(lat, lng));
        mMarker = mapView.getMap().addMarker(markeroptions);

        mapView.getEventHub().registerOnMarkerClickListener(mMarker, this);
        mapView.getEventHub().registerOnInfoWindowClickListener(mMarker, this);
    }

    /**
     * 将标记从地图中移除，同时取消必要的事件监听
     */
    public void removeFromMap() {
        mMapView.getEventHub().unregisterOnMarkerClickListener(mMarker, this);
        mMapView.getEventHub().unregisterOnInfoWindowClickListener(mMarker, this);
        mMarker.remove();
    };

    /**
     * 实现自定义的info window相关接口，自定义info window适配器由子类返回
     */
    public void setWindowAdaper() {
        mMapView.getMap().setInfoWindowAdapter(this);
    }

    /**
     * 设置是否可以点击
     */
    public void setClickable(boolean clickable) {
        mMarker.setClickable(clickable);
    }

    /**
     * 显示info window
     */
    public void showInfoWindow() {
        // 首先尝试加载本标记自定义info window
        setWindowAdaper();
        mMarker.showInfoWindow();
    }

    /**
     * 隐藏info window
     */
    public void hideInfoWindow() {
        mMarker.hideInfoWindow();
    }

    /**
     * 标记的点击事件，默认在点击时弹出自定义的info window，可由子类重写
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i(TAG, "EsMarkerBase, onMarkerClick, marker=" + marker.getId());
        showInfoWindow();
        return false;
    }

    /**
     * 标记对应的window info点击事件onInfoWindowClick，可由子类重写
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.i(TAG, "EsMarkerBase, onInfoWindowClick, marker=" + marker.getId());
    }

    /**
     * 标记对应的window info点击事件onInfoWindowClickLocation，可由子类重写
     */
    @Override
    public void onInfoWindowClickLocation(int width, int height, int x, int y) {
        Log.i(TAG, "EsMarkerBase, onInfoWindowClick, width =" + width);
        Log.i(TAG, "EsMarkerBase, onInfoWindowClick, height=" + height);
        Log.i(TAG, "EsMarkerBase, onInfoWindowClick,      x=" + x);
        Log.i(TAG, "EsMarkerBase, onInfoWindowClick,      y=" + y);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter#getInfoContents(com.tencent
     * .tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public final View getInfoContents(Marker arg0) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter#getInfoWindow(com.tencent
     * .tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public View getInfoWindow(Marker arg0) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter#getInfoWindowPressState(com
     * .tencent.tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public View getInfoWindowPressState(Marker arg0) {
        return null;
    }
}
