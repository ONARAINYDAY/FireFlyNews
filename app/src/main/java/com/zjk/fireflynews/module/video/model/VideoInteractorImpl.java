package com.zjk.fireflynews.module.video.model;

import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.http.RestApi.Api;
import com.zjk.fireflynews.http.ResultSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by FireFly on 2016/9/30 14:31.
 */
public class VideoInteractorImpl implements VideoInteractor<List<NewsData>> {
    @Override
    public Subscription asyncData(final RequestCallback<List<NewsData>> requestCallback) {
        return Observable.create(new Observable.OnSubscribe<List<NewsData>>() {

            @Override
            public void call(Subscriber<? super List<NewsData>> subscriber) {
                List<String> name = Arrays.asList("热点", "娱乐", "搞笑", "精品");
                List<String> id = Arrays.asList(String.valueOf(Api.VIDEO_HOT_ID), String.valueOf(Api.VIDEO_ENTERTAINMENT_ID), String.valueOf(Api.VIDEO_FUN_ID), String.valueOf(Api.VIDEO_CHOICE_ID));
                List<NewsData> newsDatas = new ArrayList<>();
                for (int i = 0, size = id.size(); i < size; i++) {
                    NewsData newsData = new NewsData();
                    newsData.setId(id.get(i));
                    newsData.setName(name.get(i));
                    newsDatas.add(newsData);
                }
                subscriber.onNext(newsDatas);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        requestCallback.beforeRequest();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultSubscriber<List<NewsData>>(requestCallback));
    }
}
