package com.zjk.fireflynews.module.news.view;

import com.zjk.fireflynews.base.BaseView;
import com.zjk.fireflynews.data.NewsData;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public interface NewsView extends BaseView {
    void initViewTabPager(List<NewsData> newsDatas);
}
