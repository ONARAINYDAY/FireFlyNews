package com.zjk.fireflynews.module.base.presenter;

import com.zjk.fireflynews.module.base.view.BaseView;
import com.zjk.fireflynews.callback.RequestCallback;

import rx.Subscription;

/**
 * Created by FireFly on 2016/9/7.
 */
public abstract class BasePresenterImpl<T extends BaseView, V> implements BasePresenter, RequestCallback<V> {

    protected Subscription mSubscription;
    protected T mView;

    public BasePresenterImpl(T mView){
        this.mView = mView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        if(null!=mSubscription&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mView = null;
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void requestError(String msg) {
        mView.toast(msg);
        mView.hideProgress();
    }

    @Override
    public void requestComplete() {
        mView.hideProgress();
    }
}
