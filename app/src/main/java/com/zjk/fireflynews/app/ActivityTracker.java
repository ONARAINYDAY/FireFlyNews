package com.zjk.fireflynews.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.zjk.fireflynews.utils.LogUtil;

/**
 * Created by FireFly on 2016/9/23.
 */

public class ActivityTracker {

    private static ActivityTracker instance;
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private int allActivityNum;
    private boolean isRunnForground;

    private ActivityTracker() {
        lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                log("onActivityCreated()");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                allActivityNum++;
                log("onActivityStarted()");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                log("onActivityResumed()-----current:act number is " + allActivityNum);
                isRunnForground = true;
            }

            @Override
            public void onActivityPaused(Activity activity) {
                log("onActivityPaused()");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                log("onActivityStopped()");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                log("onActivitySaveInstanceState()");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                allActivityNum--;
                log("onActivityDestroyed()");
                if (allActivityNum == 0) {
                    isRunnForground = false;
                }
            }
        };
    }

    private void log(String msg) {
        LogUtil.logd("ActivityTracker", msg);
    }

    public static ActivityTracker getInstance() {
        if (instance == null) {
            synchronized (ActivityTracker.class) {
                if (instance == null) {
                    instance = new ActivityTracker();
                }
            }
        }
        return instance;
    }

    public boolean isRunnForground() {
        return isRunnForground;
    }

    public Application.ActivityLifecycleCallbacks getLifecycleCallbacks() {
        return lifecycleCallbacks;
    }
}
