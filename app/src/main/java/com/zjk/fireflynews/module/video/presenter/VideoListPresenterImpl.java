package com.zjk.fireflynews.module.video.presenter;

import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.data.video.VideoListData;
import com.zjk.fireflynews.module.base.presenter.BaseListPresenterImpl;
import com.zjk.fireflynews.module.video.model.VideoListInteractorImpl;
import com.zjk.fireflynews.module.video.view.VideoListView;

import java.util.List;

/**
 * Created by FireFly on 2016/10/8 20:32.
 */
public class VideoListPresenterImpl extends BaseListPresenterImpl<VideoListView, List<VideoListData>> implements VideoListPresenter {

//    private NewsData newsData;
//    private VideoListInteractor mVideoListInteractor;

    public VideoListPresenterImpl(VideoListView mView, NewsData newsData) {
        super(mView, newsData);
//        this.newsData = newsData;
        mCommonListInteractor = new VideoListInteractorImpl();
    }

    @Override
    protected int getDataNum() {
        return 10;
    }

}
