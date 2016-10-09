package com.zjk.fireflynews.module.video.model;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2016/10/8 20:38.
 */
public interface VideoListInteractor<T,V> {
    Subscription asyncData(RequestCallback<T> requestCallback, V v, int mStartPage);
}
