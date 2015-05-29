package com.didi.es.map.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapClickListener;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLongClickListener;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 事件大总管，接管地图相关的常用事件的监听器的注册和分发。可按需继续扩展。 用法：EsMapView.getEventHub().registerXXX();
 * EsMapView.getEventHub().unregisterXXX();
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsMapEventHub
        implements OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener, OnMapClickListener, OnMapLongClickListener {

    private static final String TAG = EsMapEventHub.class.getSimpleName();

    /**
     * 包级权限
     */
    EsMapEventHub() {
    }

    /**
     * marker相关监听器集合。key是marker的id， value是相应的监听器。
     */
    private Map<String, OnMarkerClickListener> mOnMarkerClickListeners = new HashMap<String, OnMarkerClickListener>();
    private Map<String, OnInfoWindowClickListener> mOnInfoWindowClickListeners = new HashMap<String, OnInfoWindowClickListener>();
    private Map<String, OnMarkerDragListener> mOnMarkerDragListeners = new HashMap<String, OnMarkerDragListener>();

    /**
     * map点击相关监听器集合。
     */
    private Set<OnMapClickListener> mOnMapClickListeners = new HashSet<OnMapClickListener>();
    private Set<OnMapLongClickListener> mOnMapLongClickListeners = new HashSet<OnMapLongClickListener>();

    /**
     * 注册监听器 OnMarkerClickListener 到event hub
     * 
     * @param m
     * @param l
     */
    public void registerOnMarkerClickListener(Marker m, OnMarkerClickListener l) {
        if (null != l && null != m) {
            Log.i(TAG, "registerOnMarkerClickListener m=" + m.getId());
            mOnMarkerClickListeners.put(m.getId(), l);
        }
    }

    /**
     * 从event hub取消注册监听器 OnMarkerClickListener
     * 
     * @param m
     * @param l
     */
    public void unregisterOnMarkerClickListener(Marker m, OnMarkerClickListener l) {
        if (null != m) {
            mOnMarkerClickListeners.remove(m.getId());
        }
    }

    /**
     * 注册监听器 OnInfoWindowClickListener 到event hub
     * 
     * @param m
     * @param l
     */
    public void registerOnInfoWindowClickListener(Marker m, OnInfoWindowClickListener l) {
        if (null != l && null != m) {
            mOnInfoWindowClickListeners.put(m.getId(), l);
        }
    }

    /**
     * 从event hub取消注册监听器 OnInfoWindowClickListener
     * 
     * @param m
     * @param l
     */
    public void unregisterOnInfoWindowClickListener(Marker m, OnInfoWindowClickListener l) {
        if (null != m) {
            mOnInfoWindowClickListeners.remove(m.getId());
        }
    }

    /**
     * 注册监听器 OnMarkerDragListener 到event hub
     * 
     * @param m
     * @param l
     */
    public void registerOnMarkerDragListener(Marker m, OnMarkerDragListener l) {
        if (null != l && null != m) {
            mOnMarkerDragListeners.put(m.getId(), l);
        }
    }

    /**
     * 从event hub取消注册监听器 OnMarkerDragListener
     * 
     * @param m
     * @param l
     */
    public void unregisterOnMarkerDragListener(Marker m, OnMarkerDragListener l) {
        if (null != m) {
            mOnMarkerDragListeners.remove(m.getId());
        }
    }

    /**
     * 注册监听器 OnMapClickListener 到event hub
     * 
     * @param l
     */
    public void registerOnMapClickListener(OnMapClickListener l) {
        if (null != l) {
            mOnMapClickListeners.add(l);
        }
    }

    /**
     * 从event hub取消注册监听器 OnMapClickListener
     * 
     * @param l
     */
    public void unregisterOnMapClickListener(OnMapClickListener l) {
        if (null != l) {
            mOnMapClickListeners.remove(l);
        }
    }

    /**
     * 注册监听器 OnMapLongClickListener 到event hub
     * 
     * @param l
     */
    public void registerOnMapLongClickListener(OnMapLongClickListener l) {
        if (null != l) {
            mOnMapLongClickListeners.add(l);
        }
    }

    /**
     * 从event hub取消注册监听器 OnMapLongClickListener
     * 
     * @param l
     */
    public void unregisterOnMapLongClickListener(OnMapLongClickListener l) {
        if (null != l) {
            mOnMapLongClickListeners.remove(l);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener#onMarkerClick(com.tencent
     * .tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i(TAG, "EventHub, onMarkerClick, marker=" + marker.getId());

        Iterator<Entry<String, OnMarkerClickListener>> iterator = mOnMarkerClickListeners.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, OnMarkerClickListener> entry = iterator.next();
            String key = entry.getKey();
            if (null != key && key.equals(marker.getId())) {
                OnMarkerClickListener l = entry.getValue();
                if (null != l) {
                    Log.i(TAG, "EventHub, onMarkerClick, null != l");
                    l.onMarkerClick(marker);
                }
            }
        }
        return false;
    }

    /**
     * 临时存放OnInfoWindowClickListener相关的监听器。原因是OnInfoWindowClickListener的事件有两个，但是第二次事件未带marker参数，
     * 无法区分到底该派发给哪个监听器。
     */
    private ArrayList<OnInfoWindowClickListener> mTmpList4InfoWindowClickEvent = new ArrayList<OnInfoWindowClickListener>();

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener#onInfoWindowClick
     * (com.tencent.tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.i(TAG, "EventHub, onInfoWindowClick, marker=" + marker.getId());
        Iterator<Entry<String, OnInfoWindowClickListener>> iterator = mOnInfoWindowClickListeners.entrySet().iterator();
        synchronized (mTmpList4InfoWindowClickEvent) {
            while (iterator.hasNext()) {
                Entry<String, OnInfoWindowClickListener> entry = iterator.next();
                String key = entry.getKey();
                if (null != key && key.equals(marker.getId())) {
                    OnInfoWindowClickListener l = entry.getValue();
                    if (null != l) {
                        Log.i(TAG, "EventHub, onInfoWindowClick, null != l");
                        l.onInfoWindowClick(marker);
                        mTmpList4InfoWindowClickEvent.add(l);
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener#onInfoWindowClickLocation
     * (int, int, int, int)
     */
    @Override
    public void onInfoWindowClickLocation(int width, int height, int x, int y) {
        Log.i(TAG, "EventHub, onInfoWindowClickLocation");
        synchronized (mTmpList4InfoWindowClickEvent) {
            for (int i = mTmpList4InfoWindowClickEvent.size() - 1; i >= 0; i--) {
                mTmpList4InfoWindowClickEvent.get(i).onInfoWindowClickLocation(width, height, x, y);
            }
            mTmpList4InfoWindowClickEvent.clear();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener#onMarkerDrag(com.tencent
     * .tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public void onMarkerDrag(Marker marker) {
        Log.i(TAG, "EventHub, onMarkerDrag, marker=" + marker.getId());
        Iterator<Entry<String, OnMarkerDragListener>> iterator = mOnMarkerDragListeners.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, OnMarkerDragListener> entry = iterator.next();

            String key = entry.getKey();
            if (null != key && key.equals(marker.getId())) {
                OnMarkerDragListener l = entry.getValue();
                if (null != l) {
                    Log.i(TAG, "EventHub, onMarkerDrag, null != l");
                    l.onMarkerDrag(marker);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener#onMarkerDragEnd(com.tencent
     * .tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public void onMarkerDragEnd(Marker marker) {
        Log.i(TAG, "EventHub, onMarkerDragEnd, marker=" + marker.getId());
        Iterator<Entry<String, OnMarkerDragListener>> iterator = mOnMarkerDragListeners.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, OnMarkerDragListener> entry = iterator.next();

            String key = entry.getKey();
            if (null != key && key.equals(marker.getId())) {
                OnMarkerDragListener l = entry.getValue();
                if (null != l) {
                    Log.i(TAG, "EventHub, onMarkerDragEnd, null != l");
                    l.onMarkerDragEnd(marker);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener#onMarkerDragStart(com.
     * tencent.tencentmap.mapsdk.maps.model.Marker)
     */
    @Override
    public void onMarkerDragStart(Marker marker) {
        Log.i(TAG, "EventHub, onMarkerDragStart, marker=" + marker.getId());
        Iterator<Entry<String, OnMarkerDragListener>> iterator = mOnMarkerDragListeners.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, OnMarkerDragListener> entry = iterator.next();

            String key = entry.getKey();
            if (null != key && key.equals(marker.getId())) {
                OnMarkerDragListener l = entry.getValue();
                if (null != l) {
                    Log.i(TAG, "EventHub, onMarkerDragStart, null != l");
                    l.onMarkerDragEnd(marker);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLongClickListener#onMapLongClick(com.tencent
     * .tencentmap.mapsdk.maps.model.LatLng)
     */
    @Override
    public void onMapLongClick(LatLng latLng) {
        Log.i(TAG, "EventHub, onMapLongClick, latLng=" + latLng);
        Iterator<OnMapLongClickListener> iterator = mOnMapLongClickListeners.iterator();
        while (iterator.hasNext()) {
            OnMapLongClickListener l = iterator.next();
            if (null != l) {
                Log.i(TAG, "EventHub, onMapLongClick, null != l");
                l.onMapLongClick(latLng);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapClickListener#onMapClick(com.tencent.
     * tencentmap.mapsdk.maps.model.LatLng)
     */
    @Override
    public void onMapClick(LatLng latLng) {
        Log.i(TAG, "EventHub, onMapClick, latLng=" + latLng);
        Iterator<OnMapClickListener> iterator = mOnMapClickListeners.iterator();
        while (iterator.hasNext()) {
            OnMapClickListener l = iterator.next();
            if (null != l) {
                Log.i(TAG, "EventHub, onMapClick, null != l");
                l.onMapClick(latLng);
            }
        }
    }

    /**
     * 当EsMapView被destroy时调用，清除所有的监听器
     */
    public void clearAll() {
        Log.i(TAG, "EventHub, clearAll");
        mOnInfoWindowClickListeners.clear();
        mOnMarkerClickListeners.clear();
        mOnMarkerDragListeners.clear();
        mOnMapClickListeners.clear();
        mOnMapLongClickListeners.clear();
    }

}
