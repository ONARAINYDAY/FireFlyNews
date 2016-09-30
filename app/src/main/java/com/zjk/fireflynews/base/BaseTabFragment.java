package com.zjk.fireflynews.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.module.base.presenter.BasePresenter;
import com.zjk.fireflynews.module.base.presenter.BaseTabPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FireFly on 2016/9/30 13:32.
 */
public abstract class BaseTabFragment<T extends BaseTabPresenter> extends BaseFragment<T>{
    @BindView(R.id.tabs)
    protected TabLayout tableLayout;
    @BindView(R.id.viewpager)
    protected ViewPager viewPager;
    @Override
    protected View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
        unbinder = ButterKnife.bind(this,view);
        initPresenter();
        return view;
    }
    public abstract void initPresenter();
}
