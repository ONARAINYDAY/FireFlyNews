package com.zjk.fireflynews.module.news.model;

import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.http.RestApi.Api;
import com.zjk.fireflynews.http.RestApi.HostType;
import com.zjk.fireflynews.http.ResultSubscriber;
import com.zjk.fireflynews.http.manager.RetrofitManager;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by FireFly on 2016/9/26 13:43.
 */

public class NewsDetailInteractorImpl implements NewsDetailInteractor<List<NewsListData>,NewsData> {
    @Override
    public Subscription asyncData(RequestCallback<List<NewsListData>> requestCallback, final NewsData newsData,int mStartPage) {
        return RetrofitManager.getInstance(HostType.FIREFLY_NEWS_HOST)
                .getNewsListObservable(Api.getType(newsData.getId()),newsData.getId(),mStartPage)
                .flatMap(new Func1<Map<String, List<NewsListData>>, Observable<NewsListData>>() {
                    @Override
                    public Observable<NewsListData> call(Map<String, List<NewsListData>> map) {
                        return Observable.from(map.get(newsData.getId()));
                    }
                }).toSortedList(new Func2<NewsListData, NewsListData, Integer>() {
                    @Override
                    public Integer call(NewsListData newsListData, NewsListData newsListData2) {
                        return newsListData2.ptime.compareTo(newsListData.ptime);
                    }
                }).subscribe(new ResultSubscriber<List<NewsListData>>(requestCallback));
    }
}
