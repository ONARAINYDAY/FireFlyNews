package com.zjk.fireflynews.module.gank.model;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2017/2/8 11:40.
 */
public interface GankInteractor<T> {
    Subscription asyncData(RequestCallback<T> requestCallback, int year, int month, int day);
}
