package com.zjk.fireflynews.utils;

import android.util.Log;

/**
 * Created by FireFly on 2016/9/23.
 */

public class LogUtil {
    public static boolean LOG_H = true;

    public static void logd(String tag, String msg) {
        if (LOG_H) {
            Log.d(tag, msg);
        }
    }
}
