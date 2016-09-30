package com.zjk.fireflynews.module.video.presenter;

import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.base.presenter.BasePresenterImpl;
import com.zjk.fireflynews.module.video.model.VideoInteractorImpl;
import com.zjk.fireflynews.module.video.view.VideoView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/30 14:33.
 */
public class VideoPresenterImpl extends BasePresenterImpl<VideoView, List<NewsData>> implements VideoPresenter {
    public VideoPresenterImpl(VideoView mView) {
        super(mView);
        mSubscription = new VideoInteractorImpl().asyncData(this);
    }

    @Override
    public void requestSuccess(List<NewsData> data) {
        mView.initViewTabPager(data);
    }
}
