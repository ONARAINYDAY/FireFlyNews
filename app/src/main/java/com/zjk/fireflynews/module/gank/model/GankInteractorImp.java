package com.zjk.fireflynews.module.gank.model;

import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.data.gank.GankData;
import com.zjk.fireflynews.data.gank.GankGirl;
import com.zjk.fireflynews.data.gank.GankItem;
import com.zjk.fireflynews.data.gank.GankTitle;
import com.zjk.fireflynews.http.RestApi.HostType;
import com.zjk.fireflynews.http.ResultSubscriber;
import com.zjk.fireflynews.http.manager.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by FireFly on 2017/2/8 13:07.
 */
public class GankInteractorImp implements GankInteractor<List<GankItem>> {

    @Override
    public Subscription asyncData(final RequestCallback<List<GankItem>> requestCallback, int year, int month, int day) {
        return RetrofitManager.getInstance(HostType.GANK_DAY_HOST).getGankData(year, month, day)
                .flatMap(new Func1<GankData, Observable<List<GankItem>>>() {
                             @Override
                             public Observable<List<GankItem>> call(final GankData gankData) {
                                 return Observable.create(new Observable.OnSubscribe<List<GankItem>>() {
                                     @Override
                                     public void call(Subscriber<? super List<GankItem>> subscriber) {
                                         List<GankItem> array = new ArrayList<GankItem>();
                                         if ((gankData != null && gankData.getResults() != null && !gankData.getCategory().isEmpty())) {
                                             dealData(array, gankData);
                                             subscriber.onNext(array);
                                         } else {
                                             subscriber.onNext(null);
                                         }
                                         subscriber.onCompleted();
                                     }
                                 });
                             }
                         }
                )
                .subscribe(new ResultSubscriber<List<GankItem>>(requestCallback));
    }

    private void dealData(List<GankItem> array, GankData gankData) {

        array.addAll(gankData.getResults().get福利());

        array.add(new GankTitle(gankData.getResults().getAndroid().get(0).getType()));
        array.addAll(gankData.getResults().getAndroid());

        array.add(new GankTitle(gankData.getResults().getiOS().get(0).getType()));
        array.addAll(gankData.getResults().getiOS());

        array.add(new GankTitle(gankData.getResults().get休息视频().get(0).getType()));
        array.addAll(gankData.getResults().get休息视频());
    }
}
