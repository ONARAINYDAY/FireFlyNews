package com.zjk.fireflynews.module.news.view;

import com.zjk.fireflynews.module.base.view.BaseView;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.data.newenum.InitDataType;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public interface NewsDetailView extends BaseView {
    void updateRecycleView(List<NewsListData> newsListDataList, String msg, @InitDataType.InitDataTypeChecker int type);
}
