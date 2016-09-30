package com.zjk.fireflynews.base;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public class BaseTabPagerAdapter extends FragmentPagerAdapter {
    private List<String> titles;
    protected List<Fragment> fragments;

    public BaseTabPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? null : titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return (fragments == null && fragments.isEmpty()) ? null : fragments.get(position);
    }

}
