package com.zjk.fireflynews.module.gank.presenter;

import android.text.TextUtils;

import com.zjk.fireflynews.data.gank.GankData;
import com.zjk.fireflynews.data.gank.GankItem;
import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.module.gank.model.GankInteractor;
import com.zjk.fireflynews.module.gank.model.GankInteractorImp;
import com.zjk.fireflynews.module.gank.view.GankView;
import com.zjk.fireflynews.newenum.InitDataType;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by FireFly on 2017/2/8 09:57.
 */
public class GankPresenterImp extends BasePresenterImpl<GankView, List<GankItem>> implements GankPresenter {

    private Date currentDate;
    private final long ONE_DAY = 24 * 60 * 60 * 1000;
    private int year;
    private int month;
    private int day;
    private GankInteractor gankInteractor;
    private boolean isRefresh;

    public GankPresenterImp(GankView mView) {
        super(mView);
        currentDate = new Date();
        initParameter(currentDate);
        gankInteractor = new GankInteractorImp();
    }

    private void initParameter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onRefreshData() {
        isRefresh = true;
        unsubscribe();
        currentDate = new Date();
        initParameter(currentDate);
        mSubscription = gankInteractor.asyncData(this, year, month, day);
    }

    @Override
    public void loadMoreData() {
        isRefresh = false;
        loadAgain();
    }

    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    private void loadAgain() {
        unsubscribe();
        currentDate = new Date(currentDate.getTime() - ONE_DAY);
        initParameter(currentDate);
        mSubscription = gankInteractor.asyncData(this, year, month, day);
    }

    @Override
    public void requestError(String msg) {
        if (mView != null) {
            mView.updateRecycleView(null, msg, isRefresh ? InitDataType.TYPE_REFRESH_FAIL : InitDataType.TYPE_LOAD_MORE_FAIL);
        }
    }

    @Override
    public void requestSuccess(List<GankItem> data) {
        if (data == null || data.isEmpty()) {
            loadAgain();
            return;
        }
        if (mView != null)
            mView.updateRecycleView(data, "", isRefresh ? InitDataType.TYPE_REFRESH_SUCCESS : InitDataType.TYPE_LOAD_MORE_SUCCESS);
    }
}
