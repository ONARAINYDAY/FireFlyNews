package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2016/9/8.
 */
public interface NewsInteractor<T> {
    Subscription asyncData(RequestCallback<T> requestCallback);
}
