package com.zjk.fireflynews.module.video.ui;

import android.os.Bundle;
import android.view.View;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.module.base.ui.BaseListFragment;
import com.zjk.fireflynews.module.video.presenter.VideoListPresenter;

/**
 * Created by FireFly on 2016/9/30 14:49.
 */
public class VideoListFragment extends BaseListFragment<VideoListPresenter> {
    public static final String EXTRA_VIDEO_LIST_KEY = "extra_video_list_key";

    public static VideoListFragment newInstance(NewsData newsData) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_VIDEO_LIST_KEY, newsData);
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View view) {

    }
}
