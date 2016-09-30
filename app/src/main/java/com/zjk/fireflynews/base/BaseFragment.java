package com.zjk.fireflynews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.app.App;
import com.zjk.fireflynews.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by FireFly on 2016/9/8.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected T mPresenter;
    private View rootView;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getArgs();
        if (rootView == null) {
            rootView = onCreateViewInit(inflater, container, savedInstanceState);
        }
        if(unbinder==null){
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    protected abstract View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected void getArgs() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        App.getRefWatcher(getActivity()).watch(this);
    }

    protected void showSnackbar(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();
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
