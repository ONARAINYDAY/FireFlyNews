package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.news.NewsDetailData;
import com.zjk.fireflynews.http.RestApi.HostType;
import com.zjk.fireflynews.http.ResultSubscriber;
import com.zjk.fireflynews.http.manager.RetrofitManager;

import java.util.Map;

import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by FireFly on 2017/1/13 09:55.
 */
public class NewsDetInteractorImp implements NewsDetInteractor<NewsDetailData> {

    @Override
    public Subscription asyncNewsDetailData(RequestCallback<NewsDetailData> requestCallback, final String postId) {
        return RetrofitManager.getInstance(HostType.FIREFLY_NEWS_HOST).getNewsDetail(postId).
                map(new Func1<Map<String, NewsDetailData>, NewsDetailData>() {
                    @Override
                    public NewsDetailData call(Map<String, NewsDetailData> stringNewsDetailDataMap) {
                        return stringNewsDetailDataMap.get(postId);
                    }
                }).subscribe(new ResultSubscriber<NewsDetailData>(requestCallback));
    }
}
