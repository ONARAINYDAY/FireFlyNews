package com.zjk.fireflynews.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by FireFly on 2016/10/9 11:37.
 */
public class UiUtil {
    public static Context getContext(Object obj) {
        if (obj instanceof Activity) return (Activity) obj;
        else if (obj instanceof Fragment) return ((Fragment) obj).getActivity();
        else if (obj instanceof Context) return (Context) obj;
        else throw new RuntimeException("obj must is a activity or fragment");
    }

    public static void startActivity(Object obj, Class<?> traget) {
        startActivity(obj, traget, null);
    }

    public static void startActivity(Object obj, Class<?> traget, Bundle bundle) {
        Context context = getContext(obj);
        Intent intent = new Intent(context, traget);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
