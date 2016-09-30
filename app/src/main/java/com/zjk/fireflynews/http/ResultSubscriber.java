package com.zjk.fireflynews.http;

import com.zjk.fireflynews.app.App;
import com.zjk.fireflynews.callback.RequestCallback;
import com.zjk.fireflynews.utils.NetUtil;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by FireFly on 2016/9/26 14:24.
 * 把回调各个方法统一处理，并且这里对返回错误做了统一处理
 */
public class ResultSubscriber<T> extends Subscriber<T>{

    private RequestCallback<T> mRequestCallback;

    public ResultSubscriber(RequestCallback<T> requestCallback) {
        if(null==requestCallback){
            throw new NullPointerException("RequestCallBack is null");
        }
        mRequestCallback = requestCallback;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRequestCallback.beforeRequest();
    }

    @Override
    public void onCompleted() {
        mRequestCallback.requestComplete();
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = null;
        if (e instanceof HttpException) {
            switch (((HttpException) e).code()) {
                case 403:
                    errorMsg = "没有权限访问此链接！";
                    break;
                case 504:
                    if (!NetUtil.isConnected(App.getInstance())) {
                        errorMsg = "没有联网哦！";
                    } else {
                        errorMsg = "网络连接超时！";
                    }
                    break;
                default:
                    errorMsg = ((HttpException) e).message();
                    break;
            }
        } else if (e instanceof UnknownHostException) {
            errorMsg = "不知名主机";
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = "网络连接超时！";
        }
        mRequestCallback.requestError(errorMsg);
    }

    @Override
    public void onNext(T t) {
        mRequestCallback.requestSuccess(t);
    }
}
