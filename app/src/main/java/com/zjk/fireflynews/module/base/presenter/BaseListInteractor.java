package com.zjk.fireflynews.module.base.presenter;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2017/1/11 16:50.
 */
public interface BaseListInteractor<T, V> {
    Subscription asyncData(RequestCallback<T> requestCallback, V v, int mStartPage);
}
