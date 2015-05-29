package com.didi.es.map.base;

import android.content.Context;
import android.util.AttributeSet;

import com.tencent.tencentmap.mapsdk.maps.MapView;

/**
 * 将常用事件和事件hub绑定
 * 
 * @author houshengyong
 * @since 2015-5-26
 */

public class EsMapView extends MapView {
    private EsMapEventHub mEventHub = new EsMapEventHub();

    /**
     * @param context
     */
    public EsMapView(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public EsMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public EsMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 将事件交給event hub接管
     */
    private void init() {
        // marker related
        getMap().setOnMarkerClickListener(mEventHub);
        getMap().setOnMarkerDragListener(mEventHub);

        // info window related
        getMap().setOnInfoWindowClickListener(mEventHub);

        // map related
        getMap().setOnMapClickListener(mEventHub);
        getMap().setOnMapLongClickListener(mEventHub);

        // may be others, if necessary
    }

    @Override
    public void onDestroy() {
        mEventHub.clearAll();
        mEventHub = null;
        super.onDestroy();
    }

    /**
     * 获取EsMapView实例对应的event hub
     * 
     * @return the hub
     */
    public EsMapEventHub getEventHub() {
        return mEventHub;
    }
}
