package com.zjk.fireflynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.KeyEvent;

import com.zjk.fireflynews.base.BaseActivity;
import com.zjk.fireflynews.module.main.presenter.MainPresenter;
import com.zjk.fireflynews.module.main.view.MainView;
import com.zjk.fireflynews.module.news.ui.NewsFragment;
import com.zjk.fireflynews.utils.ExitAppHelper;
import com.zjk.fireflynews.utils.LogUtil;
import com.zjk.fireflynews.utils.ViewUtil;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private final static int CONTAINER = R.id.fragments_frame_container;
    private ExitAppHelper exitAppHelper;
    private SparseArray<Fragment> naviMapFragment = new SparseArray<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawable(null);
        ViewUtil.quitFullScreen(this);
        final String tag = NewsFragment.class.getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            fragmentTransaction.replace(CONTAINER,new NewsFragment() , tag);
            fragmentTransaction.addToBackStack(tag);
        }else{
            fragmentTransaction.replace(CONTAINER,getSupportFragmentManager().findFragmentByTag(tag),tag);
        }
        fragmentTransaction.commitAllowingStateLoss();
        exitAppHelper = new ExitAppHelper(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitAppHelper.onKeyDown(keyCode, event)|| super.onKeyDown(keyCode, event);
    }
}
