package com.zjk.fireflynews.module.video.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.zjk.fireflynews.base.BaseTabFragment;
import com.zjk.fireflynews.base.BaseTabPagerAdapter;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.video.presenter.VideoPresenter;
import com.zjk.fireflynews.module.video.presenter.VideoPresenterImpl;
import com.zjk.fireflynews.module.video.view.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FireFly on 2016/9/30 13:34.
 */
public class VideoFragment extends BaseTabFragment<VideoPresenter> implements VideoView {
    @Override
    public void initPresenter() {
        mPresenter = new VideoPresenterImpl(this);
    }

    @Override
    public void initViewTabPager(List<NewsData> newsDatas) {
        if (newsDatas == null || newsDatas.isEmpty()) return;
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (NewsData data : newsDatas) {
            fragmentList.add(VideoListFragment.newInstance(data));
            titles.add(data.getName());
        }
        BaseTabPagerAdapter adapter = new BaseTabPagerAdapter(getChildFragmentManager(), titles, fragmentList);
        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        tableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
