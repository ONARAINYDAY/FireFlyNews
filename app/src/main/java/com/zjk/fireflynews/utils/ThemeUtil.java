package com.zjk.fireflynews.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

/**
 * ClassName: ThemeUtil<p>
 * Author: oubowu<p>
 * Fuction: 主题根据类，提取当前主题的属性<p>
 * CreateDate: 2016/3/20 10:30<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class ThemeUtil {

    /**
     * 提取当前主题的颜色属性
     *
     * @param context   上下文
     * @param attrColor 属性的id
     * @return 颜色
     */
    public static int getColor(Context context, int attrColor) {
        int[] attrs = {attrColor};
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs);
        int color = ta.getColor(0, 0);
        ta.recycle();
        return color;
    }
}
