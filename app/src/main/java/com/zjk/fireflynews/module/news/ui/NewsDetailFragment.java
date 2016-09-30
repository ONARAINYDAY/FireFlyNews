package com.zjk.fireflynews.module.news.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseFragment;
import com.zjk.fireflynews.base.BaseSpaceItemDecoration;
import com.zjk.fireflynews.callback.OnLoadMoreListener;
import com.zjk.fireflynews.data.NewsData;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.data.newenum.InitDataType;
import com.zjk.fireflynews.module.adapter.NewsListAdapter;
import com.zjk.fireflynews.module.news.presenter.NewsDetailPresenter;
import com.zjk.fireflynews.module.news.presenter.NewsDetailPresenterImpl;
import com.zjk.fireflynews.module.news.view.NewsDetailView;
import com.zjk.fireflynews.utils.MeasureUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FireFly on 2016/9/8.
 */
public class NewsDetailFragment extends BaseFragment<NewsDetailPresenter> implements NewsDetailView, SwipeRefreshLayout.OnRefreshListener {

    private static String EXTRA_KEY_NEWS_DETAIL = "extra_key_news_detail";
    private NewsData newsData;
    private NewsListAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

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
    protected View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        mPresenter = new NewsDetailPresenterImpl(this, newsData);
        mPresenter.refreshData();
        return view;
    }

    private void initView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    @Override
    public void updateRecycleView(List<NewsListData> newsListDataList, String msg, @InitDataType.InitDataTypeChecker int type) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (null == adapter) {
            initNewsList(newsListDataList);
        } else {
            switch (type) {
                case InitDataType.TYPE_REFRESH_SUCCESS:
                    adapter.update(newsListDataList);
                    break;
                case InitDataType.TYPE_LOAD_MORE_SUCCESS:
//                    adapter.setShowFooter(false);
                    adapter.onLoadMoreSuccess();
                    adapter.addTail(newsListDataList);
                    break;
                case InitDataType.TYPE_REFRESH_FAIL:
                    break;
                case InitDataType.TYPE_LOAD_MORE_FAIL:
                    adapter.onLoadMoreFail(msg);
                    break;
            }
        }
    }

    private void initNewsList(List<NewsListData> newsListDataList) {
        adapter = new NewsListAdapter(getActivity(), newsListDataList);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new BaseSpaceItemDecoration(MeasureUtil.dip2px(getActivity(), 4)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(adapter);
    }

}
