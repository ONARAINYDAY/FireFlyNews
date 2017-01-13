package com.zjk.fireflynews.module.video.model;

import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.data.VideoListData;
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
 * Created by FireFly on 2016/10/8 20:41.
 */
public class VideoListInteractorImpl implements VideoListInteractor<List<VideoListData>, NewsData> {
    @Override
    public Subscription asyncData(final RequestCallback<List<VideoListData>> requestCallback, final NewsData newsData, int mStartPage) {
        return RetrofitManager.getInstance(HostType.FIREFLY_NEWS_HOST)
                .getVideoListObservable(newsData.getId(), mStartPage)
                .flatMap(new Func1<Map<String, List<VideoListData>>, Observable<VideoListData>>() {
                    @Override
                    public Observable<VideoListData> call(Map<String, List<VideoListData>> map) {
                        return Observable.from(map.get(newsData.getId()));
                    }
                }).toSortedList(new Func2<VideoListData, VideoListData, Integer>() {
                    @Override
                    public Integer call(VideoListData newsListData, VideoListData newsListData2) {
                        return newsListData2.getPtime().compareTo(newsListData.getPtime());
                    }
                }).subscribe(new ResultSubscriber<List<VideoListData>>(requestCallback));
    }
}
