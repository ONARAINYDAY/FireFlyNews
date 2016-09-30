package com.zjk.fireflynews.module.news.presenter;

import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.news.model.NewsInteractorImpl;
import com.zjk.fireflynews.module.news.view.NewsView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public class NewsPresenterImpl extends BasePresenterImpl<NewsView, List<NewsData>> implements NewsPresenter {

    public NewsPresenterImpl(NewsView mView) {
        super(mView);
        new NewsInteractorImpl().asyncData(this);
    }

    @Override
    public void requestSuccess(List<NewsData> data) {
        mView.initViewTabPager(data);
    }
}
