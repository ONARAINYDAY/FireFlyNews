package com.zjk.fireflynews.module.base.view;

import com.zjk.fireflynews.data.newenum.InitDataType;

import java.util.List;

/**
 * Created by FireFly on 2016/9/30 16:38.
 */
public interface BaseListView<T> extends BaseView {
    void updateRecycleView(List<T> list, String msg, @InitDataType.InitDataTypeChecker int type);
}
