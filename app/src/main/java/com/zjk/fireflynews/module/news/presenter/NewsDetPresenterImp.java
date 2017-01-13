package com.zjk.fireflynews.module.news.presenter;

import com.zjk.fireflynews.data.news.NewsDetailData;
import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.module.news.model.NewsDetInteractor;
import com.zjk.fireflynews.module.news.model.NewsDetInteractorImp;
import com.zjk.fireflynews.module.news.view.NewsDetActView;

/**
 * Created by FireFly on 2017/1/13 09:41.
 */
public class NewsDetPresenterImp extends BasePresenterImpl<NewsDetActView, NewsDetailData> implements NewsDetPresenter {

    public NewsDetPresenterImp(NewsDetActView mView, String postId) {
        super(mView);
        mSubscription = new NewsDetInteractorImp().asyncNewsDetailData(this, postId);
    }

    @Override
    public void requestSuccess(NewsDetailData data) {
        mView.initUi(data);
    }

}
