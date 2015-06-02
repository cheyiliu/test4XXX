package com.didi.biz.editor.container;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-2
 */

public interface ICreatorItemInputCallback {
    // 请求乘客信息及回调
    void requestPassenger();

    void onPassengerSelected();

    // 请求乘客信息及回调
    void requestFlight();

    void onFlightSelected();

    // 请求乘客信息及回调
    void requestStartTime();

    void onStartTimeSelected();

    // 请求乘客信息及回调
    void requestFromPos();

    void onFromPosSelected();

    // 请求乘客信息及回调
    void requestToPos();

    void onToPosSelected();

    // 请求乘客信息及回调
    void requestRentTime();

    void onRentTimeSelected();

    // 请求乘客信息及回调
    void requestCarType();

    void onCarTypeSelected();

}
