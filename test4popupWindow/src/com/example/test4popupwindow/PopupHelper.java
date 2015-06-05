package com.example.test4popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
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
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);
    }
}
