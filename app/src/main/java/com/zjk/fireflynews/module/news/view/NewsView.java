package com.zjk.fireflynews.module.news.view;

import com.zjk.fireflynews.module.base.view.BaseView;
import com.zjk.fireflynews.data.news.NewsData;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public interface NewsView extends BaseView {
    void initViewTabPager(List<NewsData> newsDatas);
}
