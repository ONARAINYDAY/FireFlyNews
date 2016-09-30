package com.zjk.fireflynews.module.news.presenter;

import com.zjk.fireflynews.base.BasePresenter;

/**
 * Created by FireFly on 2016/9/8.
 */
public interface NewsDetailPresenter extends BasePresenter {
    /**
     * 刷新数据
     */
    void refreshData();

    /**
     * 加载更多
     */
    void loadMoreData();

}
