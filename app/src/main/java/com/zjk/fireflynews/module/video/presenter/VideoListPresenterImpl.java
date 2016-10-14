package com.zjk.fireflynews.module.video.presenter;

import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.VideoListData;
import com.zjk.fireflynews.module.base.presenter.BaseListPresenterImpl;
import com.zjk.fireflynews.module.video.model.VideoListInteractor;
import com.zjk.fireflynews.module.video.model.VideoListInteractorImpl;
import com.zjk.fireflynews.module.video.view.VideoListView;

import java.util.List;

/**
 * Created by FireFly on 2016/10/8 20:32.
 */
public class VideoListPresenterImpl extends BaseListPresenterImpl<VideoListView, List<VideoListData>> implements VideoListPresenter {

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
    protected int getDataNum() {
        return 10;
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
