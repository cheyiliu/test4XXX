package com.didi.es.map.helper;

import com.didi.es.map.base.EsMapView;
import com.tencent.tencentmap.mapsdk.maps.InfoWindowAnimationManager;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationListener;

/**
 * 地图info window动画辅助类
 *
 * @author houshengyong
 * @since 2015-5-27
 */

public class EsMapInfoWindowAnimationHelper {
    private EsMapInfoWindowAnimationHelper(){}
    
    /**
     * 应用xxx动画到info window上
     * @param mapView
     * @param listener
     */
    public static void applyInfoWindowXXXAnimation (EsMapView mapView, AnimationListener listener) {
        if (null != mapView){
            InfoWindowAnimationManager infoWindowAnimationManager = mapView.getMap().getInfoWindowAnimationManager();
            if (null != infoWindowAnimationManager) {
                Animation animation=null;// TODO 具体动画
                infoWindowAnimationManager.setInfoWindowAnimation(animation, listener);
                infoWindowAnimationManager.startAnimation();
            }      
        }
  
    }

}
