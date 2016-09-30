package com.zjk.fireflynews.module.main.presenter;

import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.main.view.MainView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/7.
 */
public class MainPresenterImpl extends BasePresenterImpl<MainView, List<NewsData>> implements MainPresenter {

    public MainPresenterImpl(MainView mView) {
        super(mView);
    }

    @Override
    public void requestSuccess(List<NewsData> data) {
    }
}
