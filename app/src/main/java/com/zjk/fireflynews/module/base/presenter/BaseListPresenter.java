package com.zjk.fireflynews.module.base.presenter;

/**
 * Created by FireFly on 2016/9/30 15:08.
 */
public interface BaseListPresenter extends BasePresenter {
    /**
     * 下拉刷新
     */
    void onRefreshData();

    /**
     * 加载更多
     */
    void loadMoreData();
}
