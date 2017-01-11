package com.zjk.fireflynews.module.news.presenter;

import com.zjk.fireflynews.module.base.presenter.BaseListPresenterImpl;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.module.news.model.NewsDetailInteractor;
import com.zjk.fireflynews.module.news.model.NewsDetailInteractorImpl;
import com.zjk.fireflynews.module.news.view.NewsDetailView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/26 10:14.
 */

public class NewsDetailPresenterImpl extends BaseListPresenterImpl<NewsDetailView, List<NewsListData>> implements NewsDetailPresenter {

//    private NewsData newsData;
//    private NewsDetailInteractor newsDetailInteractor;

    public NewsDetailPresenterImpl(NewsDetailView mView, NewsData newsData) {
        super(mView,newsData);
//        if (newsData == null) {
//            throw new NullPointerException("newsData is null");
//        }
//        this.newsData = newsData;
        mCommonListInteractor = new NewsDetailInteractorImpl();
    }

    @Override
    protected int getDataNum() {
        return 20;
    }

//    @Override
//    public void onRefreshData() {
//        unsubscribe();
//        isRefresh = true;
//        mStartPage = 0;
//        mSubscription = newsDetailInteractor.asyncData(this, newsData, mStartPage);
//    }
//
//    @Override
//    public void loadMoreData() {
//        unsubscribe();
//        isRefresh = false;
//        mSubscription = newsDetailInteractor.asyncData(this, newsData, mStartPage);
//    }
}
