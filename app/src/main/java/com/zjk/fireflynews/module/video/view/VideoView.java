package com.zjk.fireflynews.module.video.view;

import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.module.base.view.BaseView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/30 13:45.
 */
public interface VideoView extends BaseView {
    void initViewTabPager(List<NewsData> newsDatas);
}
