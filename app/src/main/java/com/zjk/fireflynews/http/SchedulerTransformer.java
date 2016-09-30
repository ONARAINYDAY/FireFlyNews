package com.zjk.fireflynews.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FireFly on 2016/9/26 09:56.
 * 定义了一个默认的线程模型，大多数情况下，我们都会在 io 线程发起 request，在主线程处理 response
 */

public class SchedulerTransformer<T> implements Observable.Transformer<T,T> {
    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
