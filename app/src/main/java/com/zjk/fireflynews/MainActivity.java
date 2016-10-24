package com.zjk.fireflynews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;

import com.zjk.fireflynews.base.BaseActivity;
import com.zjk.fireflynews.module.main.presenter.MainPresenter;
import com.zjk.fireflynews.module.main.presenter.MainPresenterImpl;
import com.zjk.fireflynews.module.main.view.MainView;
import com.zjk.fireflynews.module.news.ui.NewsFragment;
import com.zjk.fireflynews.module.photo.ui.PhotoFragment;
import com.zjk.fireflynews.module.video.ui.VideoFragment;
import com.zjk.fireflynews.utils.ExitAppHelper;
import com.zjk.fireflynews.utils.LogUtil;
import com.zjk.fireflynews.utils.ViewUtil;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private final static int CONTAINER = R.id.fragments_frame_container;
    private ExitAppHelper exitAppHelper;
    private SparseArray<Fragment> naviMapFragment = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.initView();

    }

    @Override
    public void initMainView() {
        getWindow().setBackgroundDrawable(null);
        ViewUtil.quitFullScreen(this);
        naviMapFragment.clear();

        addFragment(new NewsFragment(), R.id.firefly_news);
        addFragment(new PhotoFragment(), R.id.firefly_photo);
        addFragment(new VideoFragment(), R.id.firefly_video);

        replaceFragment(getSupportFragmentManager(), R.id.firefly_news);

        exitAppHelper = new ExitAppHelper(this);
    }

    private void replaceFragment(FragmentManager fm, int viewId) {
        String tag = String.valueOf(viewId);
        FragmentTransaction transaction = fm.beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            transaction.replace(CONTAINER, naviMapFragment.get(viewId), tag);
            transaction.addToBackStack(tag);
        } else {
            transaction.replace(CONTAINER, getSupportFragmentManager().findFragmentByTag(tag), tag);
        }
        transaction.commitAllowingStateLoss();
        //
        for (int i = 0, size = naviMapFragment.size(); i < size; i++) {
            int curId = naviMapFragment.keyAt(i);
            if (curId == viewId) {
                findViewById(viewId).setSelected(true);
            } else {
                findViewById(curId).setSelected(false);
            }
        }
    }

    private void addFragment(Fragment fragment, int id) {
        View view = findViewById(id);
        view.setSelected(false);
        naviMapFragment.put(id, fragment);
    }


    private void switchFragment(View view) {
        int viewId = view.getId();
        if (naviMapFragment.indexOfKey(viewId) < 0) return;
        if (!view.isSelected()) {
            replaceFragment(getSupportFragmentManager(), viewId);
        }
    }


    @OnClick({R.id.firefly_news, R.id.firefly_photo, R.id.firefly_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.firefly_news:
            case R.id.firefly_photo:
            case R.id.firefly_video:
                switchFragment(view);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitAppHelper.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

}
