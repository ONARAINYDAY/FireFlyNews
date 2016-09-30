package com.zjk.fireflynews.module.news.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseFragment;
import com.zjk.fireflynews.base.BaseTabFragment;
import com.zjk.fireflynews.base.BaseTabPagerAdapter;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.news.presenter.NewsPresenter;
import com.zjk.fireflynews.module.news.presenter.NewsPresenterImpl;
import com.zjk.fireflynews.module.news.view.NewsView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FireFly on 2016/9/8.
 */
public class NewsFragment /*extends BaseFragment<NewsPresenter>*/extends BaseTabFragment<NewsPresenter> implements NewsView {

    //    http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
//   (@Path("type") String type, @Path("id") String id,@Path("startPage") int startPage)
//    @BindView(R.id.tabs)
//    TabLayout tableLayout;
//    @BindView(R.id.viewpager)
//    ViewPager viewPager;
//
//    protected View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
//        unbinder = ButterKnife.bind(this,view);
//        mPresenter = new NewsPresenterImpl(this);
//        return view;
//    }

    @Override
    public void initViewTabPager(List<NewsData> newsDatas) {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (NewsData data : newsDatas) {
            fragmentList.add(NewsDetailFragment.getInstance(data));
            titles.add(data.getName());
        }
        BaseTabPagerAdapter adapter = new BaseTabPagerAdapter(getChildFragmentManager(),titles,fragmentList);
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsPresenterImpl(this);
    }
}
