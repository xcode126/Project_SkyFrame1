package com.sky.skyframe1;

import android.app.Application;

import com.sky.skyframe1.method.GHInitConfig;

/**
 * Author：sky on 2018/5/8 0008 09:49.
 * Email：xcode126@126.com
 * Desc：代表整个App应用程序
 */

public class BaseApplication extends Application{
    public static float sScale;
    public static int sWidthDp;
    public static int sWidthPix;

    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sScale = getResources().getDisplayMetrics().density;
        sWidthPix = getResources().getDisplayMetrics().widthPixels;
        sWidthDp = (int) (sWidthPix / sScale);
        GHInitConfig.getInstance().initPlugIn();
    }
}
