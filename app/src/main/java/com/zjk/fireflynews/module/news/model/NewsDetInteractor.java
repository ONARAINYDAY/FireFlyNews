package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2017/1/13 09:54.
 */
public interface NewsDetInteractor<T> {
    Subscription asyncNewsDetailData(RequestCallback<T> requestCallback, String postId);
}
