package com.example.test4popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Class description
 * 
 * @author houshengyong
 * @since 2015-6-5
 */

public class PopupHelper {
    // Display popup attached to the button as a position anchor
    @SuppressWarnings("deprecation")
    public static void displayPopupWindow(Context context, LayoutInflater inflater, View anchorView) {
        PopupWindow popup = new PopupWindow(context.getApplicationContext());
        View layout = inflater.inflate(R.layout.popup_content, null);
        popup.setContentView(layout);

        // Set content width and height
        // http://stackoverflow.com/questions/7696246/popupwindow-out-of-screen-when-size-is-unspecified
        // http://stackoverflow.com/questions/29746432/how-to-make-custom-popup-window-in-listview-item-android

        // 在边缘处会被遮挡
        // popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        // 在边缘处不会被遮挡 ---OK，估计值，popup最大宽高
        popup.setHeight(200);
        popup.setWidth(500);

        // 在边缘处会被遮挡
        // popup.setWindowLayoutMode(WindowManager.LayoutParams.WRAP_CONTENT,
        // WindowManager.LayoutParams.WRAP_CONTENT);

        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);

        // popup.showAsDropDown(anchorView, 0, 0, Gravity.TOP);// api level 19
        // popup.showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0);
    }
}
