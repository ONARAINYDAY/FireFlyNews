package com.zjk.fireflynews.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.module.base.presenter.BasePresenter;
import com.zjk.fireflynews.module.base.view.BaseView;
import com.zjk.fireflynews.utils.ThemeUtil;
import com.zjk.fireflynews.utils.ViewUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FireFly on 2016/9/6.
 */
public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;
    private Unbinder unbinder;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    protected void statusBarView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // 创建View,为了设置StatusBar的颜色
            View statusView = ViewUtil.createStatusView(this, ThemeUtil.getColor(this, R.attr.colorPrimaryDark));
            // 获得根视图并把TextView加进去。
            ViewGroup contentView = (ViewGroup) this.findViewById(android.R.id.content);
            contentView.addView(statusView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
        if(null!=unbinder){
            unbinder.unbind();
        }
    }

    protected void showSnackbar(String msg) {
        Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void toast(String msg) {
        showSnackbar(msg);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
