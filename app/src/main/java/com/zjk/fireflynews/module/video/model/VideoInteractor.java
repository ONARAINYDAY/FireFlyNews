package com.zjk.fireflynews.module.video.model;

import com.zjk.fireflynews.callback.RequestCallback;
import rx.Subscription;

/**
 * Created by FireFly on 2016/9/30 14:29.
 */
public interface VideoInteractor<T> {
    Subscription asyncData(RequestCallback<T> requestCallback);
}
