package com.zjk.fireflynews.module.video.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.data.C;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.VideoListData;
import com.zjk.fireflynews.module.adapter.VideoListAdapter;
import com.zjk.fireflynews.module.base.ui.BaseListFragment;
import com.zjk.fireflynews.module.video.presenter.VideoListPresenter;
import com.zjk.fireflynews.module.video.presenter.VideoListPresenterImpl;
import com.zjk.fireflynews.module.video.view.VideoListView;
import com.zjk.fireflynews.utils.UiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FireFly on 2016/9/30 14:49.
 */
public class VideoListFragment extends BaseListFragment<VideoListPresenter,VideoListData> implements VideoListView {
    public static final String EXTRA_VIDEO_LIST_KEY = "extra_video_list_key";
    private NewsData newsData;
    private RecyclerView.LayoutManager layoutManager;
    public static VideoListFragment newInstance(NewsData newsData) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_VIDEO_LIST_KEY, newsData);
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getArgs() {
        Bundle bundle = getArguments();
        newsData = bundle.getParcelable(EXTRA_VIDEO_LIST_KEY);
    }

    @Override
    public void initView(View view) {
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mPresenter = new VideoListPresenterImpl(this,newsData);
        mPresenter.onRefreshData();
    }

    @Override
    public BaseRecyclerViewAdapter<VideoListData> getAdapter() {
        return new VideoListAdapter(getActivity(),null,layoutManager);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    public void setListener(List<VideoListData> list, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(C.EXTRA_URL_KEY,list.get(position));
        UiUtil.startActivity(VideoListFragment.this,PlayVideoActivity.class,bundle);
    }
}
