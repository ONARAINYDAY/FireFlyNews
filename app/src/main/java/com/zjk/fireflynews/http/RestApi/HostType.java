package com.zjk.fireflynews.http.RestApi;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by FireFly on 2016/9/23.
 */

public class HostType {
    @IntDef({FIREFLY_VIDEO_HOST,FIREFLY_PHOTO_HOST,FIREFLY_NEWS_HOST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker{}
    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    /**
     * 视频的host
     */
    @HostTypeChecker
    public static final int FIREFLY_VIDEO_HOST = 1;

    /**
     * 图片的host
     */
    @HostTypeChecker
    public static final int FIREFLY_PHOTO_HOST = 2;

    /**
     * 新闻的host
     */
    @HostTypeChecker
    public static final int FIREFLY_NEWS_HOST = 3;
}
