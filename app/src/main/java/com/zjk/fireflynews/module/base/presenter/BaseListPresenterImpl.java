package com.zjk.fireflynews.module.base.presenter;

import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.newenum.InitDataType;
import com.zjk.fireflynews.module.base.view.BaseListView;

import java.util.List;

/**
 * Created by FireFly on 2016/10/12 14:20.
 */
public abstract class BaseListPresenterImpl<T extends BaseListView, V extends List> extends BasePresenterImpl<T, V> implements BaseListPresenter {

    protected boolean isRefresh;
    protected int mStartPage;
    protected BaseListInteractor mCommonListInteractor;
    protected NewsData newsData;

    public BaseListPresenterImpl(T mView, NewsData newsData) {
        super(mView);
        if (newsData == null) {
            throw new NullPointerException("video NewsData is null");
        }
        this.newsData = newsData;
    }

    @Override
    public void requestError(String msg) {
//        super.requestError(msg);
        if (mView != null) {
            mView.updateRecycleView(null, msg, isRefresh ? InitDataType.TYPE_REFRESH_FAIL : InitDataType.TYPE_LOAD_MORE_FAIL);
        }
    }

    @Override
    public void requestSuccess(V data) {
        if (data != null || !data.isEmpty()) {
            mStartPage += getDataNum();
        }
        if (mView != null)
            mView.updateRecycleView(data, "", isRefresh ? InitDataType.TYPE_REFRESH_SUCCESS : InitDataType.TYPE_LOAD_MORE_SUCCESS);
    }

    protected abstract int getDataNum();

    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    @Override
    public void onRefreshData() {
        unsubscribe();
        isRefresh = true;
        mStartPage = 0;
        mSubscription = mCommonListInteractor.asyncData(this, newsData, mStartPage);
    }

    @Override
    public void loadMoreData() {
        unsubscribe();
        isRefresh = false;
        mSubscription = mCommonListInteractor.asyncData(this, newsData, mStartPage);
    }
}
