package com.didi.es.map.marker;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.didi.es.map.base.EsMapView;
import com.didi.es.map.base.EsMarkerBase;
import com.example.test4txmap.EsApp;
import com.example.test4txmap.R;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;

/**
 * 通用标记，该标记具有单行info window信息展示。可用在车辆改派中，司机位置信息提醒等单行文本提醒处。
 * 该类新增设置文本内容的api, {@link #setTextInfo(String)}
 * 
 * @author houshengyong
 * @since 2015-6-4
 */

public class EsSingleLineInfoMarker extends EsMarkerBase {

    private View mSingleLineInfoView;
    private TextView mTextViewContent;

    /**
     * @param mapView
     * @param iconId
     * @param lat
     * @param lng
     */
    public EsSingleLineInfoMarker(EsMapView mapView, int iconId, double lat, double lng) {
        super(mapView, iconId, lat, lng);
        mSingleLineInfoView = LayoutInflater.from(EsApp.sAppContext).inflate(R.layout.map_marker_with_single_line_info_popup, null);
        mTextViewContent = (TextView) mSingleLineInfoView.findViewById(R.id.tv_content);
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        return mSingleLineInfoView;
    }

    /**
     * 设置更新info window里的显示内容
     * 
     * @param content
     */
    public void setTextInfo(String content) {
        mTextViewContent.setText(content);
        showInfoWindow();
    }
}
