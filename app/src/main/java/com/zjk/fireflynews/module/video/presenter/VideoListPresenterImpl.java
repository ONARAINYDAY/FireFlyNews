package com.zjk.fireflynews.module.video.presenter;

import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.data.VideoListData;
import com.zjk.fireflynews.data.newenum.InitDataType;
import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.module.video.model.VideoListInteractor;
import com.zjk.fireflynews.module.video.model.VideoListInteractorImpl;
import com.zjk.fireflynews.module.video.view.VideoListView;

import java.util.List;

/**
 * Created by FireFly on 2016/10/8 20:32.
 */
public class VideoListPresenterImpl extends BasePresenterImpl<VideoListView, List<VideoListData>> implements VideoListPresenter {

    private int mStartPage;
    private boolean isRefresh;
    private NewsData newsData;
    private VideoListInteractor mVideoListInteractor;

    public VideoListPresenterImpl(VideoListView mView, NewsData newsData) {
        super(mView);
        if (newsData == null) {
            throw new NullPointerException("video NewsData is null");
        }
        this.newsData = newsData;
        mVideoListInteractor = new VideoListInteractorImpl();
    }

    @Override
    public void requestSuccess(List<VideoListData> data) {
        if (data != null || !data.isEmpty()) {
            mStartPage += 10;
        }
        mView.updateRecycleView(data, "", isRefresh ? InitDataType.TYPE_REFRESH_SUCCESS : InitDataType.TYPE_LOAD_MORE_SUCCESS);
    }

    @Override
    public void requestError(String msg) {
        mView.updateRecycleView(null, msg, isRefresh ? InitDataType.TYPE_REFRESH_FAIL : InitDataType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void onRefreshData() {
        isRefresh = true;
        mStartPage = 0;
        mSubscription = mVideoListInteractor.asyncData(this, newsData, mStartPage);
    }

    @Override
    public void loadMoreData() {
        isRefresh = false;
        mSubscription = mVideoListInteractor.asyncData(this, newsData, mStartPage);
    }
}
