package com.zjk.fireflynews.module.base.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseFragment;
import com.zjk.fireflynews.module.base.presenter.BaseListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FireFly on 2016/9/30 15:00.
 */
public abstract class BaseListFragment<T extends BaseListPresenter> extends BaseFragment<T> implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        initView(view);
        return view;
    }

    public abstract void initView(View view);

    @Override
    public void onRefresh() {
        if(mPresenter!=null)
        mPresenter.onRefreshData();
    }
}
