package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.app.App;
import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.news.NewsData;
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
 * Created by FireFly on 2016/9/8.
 */
public class NewsInteractorImpl implements NewsInteractor<List<NewsData>> {
    @Override
    public Subscription asyncData(final RequestCallback<List<NewsData>> requestCallback) {
        return Observable.create(new Observable.OnSubscribe<List<NewsData>>() {
            @Override
            public void call(Subscriber<? super List<NewsData>> subscriber) {
                List<String> nameList = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.news_name));
                List<String> idList = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.news_id));
                List<NewsData> newsDatas = new ArrayList<>();
                for (int i = 0, size = nameList.size(); i < size; i++) {
                    NewsData newsData = new NewsData();
                    newsData.setId(idList.get(i));
                    newsData.setName(nameList.get(i));
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
