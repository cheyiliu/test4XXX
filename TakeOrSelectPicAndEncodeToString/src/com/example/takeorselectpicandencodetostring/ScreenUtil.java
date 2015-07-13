package com.example.takeorselectpicandencodetostring;

import android.util.DisplayMetrics;

public class ScreenUtil {
    private final String TAG = "SystemParams";
    private volatile static ScreenUtil params;
    public int screenWidth;// 屏幕宽度，单位为px
    public int screenHeight;// 屏幕高度，单位为px
    public int densityDpi;// 屏幕密度，单位为dpi
    public float scale;// 缩放系数，值为 densityDpi/160
    public float fontScale;// 文字缩放系数，同scale

    public final static int SCREEN_ORIENTATION_VERTICAL = 1; // 屏幕状态：横屏
    public final static int SCREEN_ORIENTATION_HORIZONTAL = 2; // 屏幕状态：竖屏
    public int screenOrientation = SCREEN_ORIENTATION_VERTICAL;// 当前屏幕状态，默认为竖屏

    /**
     * 私有构造方法
     * 
     * @param activity
     */
    private ScreenUtil() {
        DisplayMetrics dm = BaseApplication.getAppContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        densityDpi = dm.densityDpi;
        scale = dm.density;
        fontScale = dm.scaledDensity;
        screenOrientation = screenHeight > screenWidth ? SCREEN_ORIENTATION_VERTICAL : SCREEN_ORIENTATION_HORIZONTAL;
    }

    /**
     * 获取实例
     * 
     * @param activity
     * @return
     */
    public static ScreenUtil getInstance() {
        if (params == null) {
            synchronized (ScreenUtil.class) {
                if (params == null) {
                    params = new ScreenUtil();
                }
            }
        }
        return params;
    }

    /**
     * 参数信息
     */
    public String toString() {

        return TAG
                + ":[screenWidth: " + screenWidth + " screenHeight: " + screenHeight + " scale: " + scale + " fontScale: " + fontScale
                + " densityDpi: " + densityDpi + " screenOrientation: "
                + (screenOrientation == SCREEN_ORIENTATION_VERTICAL ? "vertical" : "horizontal") + "]";
    }

    public static int px2dip(float pxValue) {
        final float scale = getInstance().scale;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float scale = getInstance().scale;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        final float scale = getInstance().scale;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getWidth() {
        return getInstance().screenWidth;
    }

    public static int getHeight() {
        return getInstance().screenHeight;
    }

    /**
     * 获取屏幕密度
     * 
     * @param pxValue
     * @return
     */
    public static float getScale() {
        return getInstance().scale;
    }

}