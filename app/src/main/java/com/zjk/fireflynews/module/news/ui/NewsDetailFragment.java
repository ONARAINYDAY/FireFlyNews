package com.zjk.fireflynews.module.news.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.data.news.NewsData;
import com.zjk.fireflynews.data.news.NewsListData;
import com.zjk.fireflynews.module.adapter.NewsListAdapter;
import com.zjk.fireflynews.module.base.ui.BaseListFragment;
import com.zjk.fireflynews.module.news.presenter.NewsDetailPresenter;
import com.zjk.fireflynews.module.news.presenter.NewsDetailPresenterImpl;
import com.zjk.fireflynews.module.news.view.NewsDetailView;

import java.util.List;

/**
 * Created by FireFly on 2016/9/8.
 */
public class NewsDetailFragment extends BaseListFragment<NewsDetailPresenter, NewsListData> implements NewsDetailView {

    private static String EXTRA_KEY_NEWS_DETAIL = "extra_key_news_detail";
    private NewsData newsData;

    public static NewsDetailFragment getInstance(NewsData newsData) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_KEY_NEWS_DETAIL, newsData);
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    @Override
    protected void getArgs() {
        Bundle bundle = getArguments();
        newsData = bundle.getParcelable(EXTRA_KEY_NEWS_DETAIL);
    }

    @Override
    public void initView(View view) {
        mPresenter = new NewsDetailPresenterImpl(this, newsData);
        mPresenter.onRefreshData();
    }

    @Override
    public BaseRecyclerViewAdapter<NewsListData> getAdapter() {
        return new NewsListAdapter(getActivity(), null);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void setListener(List<NewsListData> list, int position) {
        if (list == null || list.isEmpty()) return;
        NewsListData listData = list.get(position);
        if (!TextUtils.isEmpty(listData.getPostid())) {
            NewsDetailActivity.startNewsDetailInfo(getActivity(), listData.getPostid(), listData.getImgsrc());
        } else {
            showSnackbar("敬请期待");
        }
    }
}
