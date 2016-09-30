package com.zjk.fireflynews.module.news.presenter;

import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.data.newenum.InitDataType;
import com.zjk.fireflynews.module.news.model.NewsDetailInteractor;
import com.zjk.fireflynews.module.news.model.NewsDetailInteractorImpl;
import com.zjk.fireflynews.module.news.view.NewsDetailView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/26 10:14.
 */

public class NewsDetailPresenterImpl extends BasePresenterImpl<NewsDetailView, List<NewsListData>> implements NewsDetailPresenter {

    private int mStartPage;
    private boolean isRefresh;
    private NewsData newsData;
    private NewsDetailInteractor newsDetailInteractor;

    public NewsDetailPresenterImpl(NewsDetailView mView, NewsData newsData) {
        super(mView);
        if (newsData == null) {
            throw new NullPointerException("newsData is null");
        }
        this.newsData = newsData;
//        mStartPage = 0;
//        isRefresh = true;
        newsDetailInteractor = new NewsDetailInteractorImpl();
//        mSubscription = newsDetailInteractor.asyncData(this, newsData, mStartPage);
    }

    @Override
    public void requestError(String msg) {
//        super.requestError(msg);
        mView.updateRecycleView(null, msg, isRefresh ? InitDataType.TYPE_REFRESH_FAIL : InitDataType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void requestSuccess(List<NewsListData> data) {
        if (data != null || !data.isEmpty()) {
            mStartPage += 20;
        }
        mView.updateRecycleView(data, "", isRefresh ? InitDataType.TYPE_REFRESH_SUCCESS : InitDataType.TYPE_LOAD_MORE_SUCCESS);
    }

    @Override
    public void onRefreshData() {
        isRefresh = true;
        mStartPage = 0;
        mSubscription = newsDetailInteractor.asyncData(this, newsData, mStartPage);
    }

    @Override
    public void loadMoreData() {
        isRefresh = false;
        mSubscription = newsDetailInteractor.asyncData(this, newsData, mStartPage);
    }
}
