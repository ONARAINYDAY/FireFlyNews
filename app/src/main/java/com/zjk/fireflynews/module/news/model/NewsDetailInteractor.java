package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2016/9/26 14:01.
 */

public interface NewsDetailInteractor<T,V> {
    Subscription asyncData(RequestCallback<T> requestCallback, V v,int mStartPage);
}
