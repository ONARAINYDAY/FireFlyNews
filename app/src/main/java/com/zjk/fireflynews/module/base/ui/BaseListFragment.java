package com.zjk.fireflynews.module.base.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseFragment;
import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.base.BaseSpaceItemDecoration;
import com.zjk.fireflynews.callback.OnItemClickListener;
import com.zjk.fireflynews.callback.OnLoadMoreListener;
import com.zjk.fireflynews.newenum.InitDataType;
import com.zjk.fireflynews.module.base.presenter.BaseListPresenter;
import com.zjk.fireflynews.module.base.view.BaseListView;
import com.zjk.fireflynews.utils.MeasureUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by FireFly on 2016/9/30 15:00.
 */
public abstract class BaseListFragment<T extends BaseListPresenter, V> extends BaseFragment<T> implements SwipeRefreshLayout.OnRefreshListener,
        BaseListView<V> {
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseRecyclerViewAdapter<V> adapter;

    @Override
    protected View onCreateViewInit(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        initView(view);
        return view;
    }

    public abstract void initView(View view);

    @Override
    public void onRefresh() {
        if (mPresenter != null)
            mPresenter.onRefreshData();
    }

    @Override
    public void updateRecycleView(List<V> list, String msg, @InitDataType.InitDataTypeChecker int type) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (adapter == null) {
            initNewsList();
        }
        adapter.onShowEmptyView(false, msg);
        switch (type) {
            case InitDataType.TYPE_REFRESH_SUCCESS:
                adapter.update(list);
                break;
            case InitDataType.TYPE_LOAD_MORE_SUCCESS:
//                    adapter.setShowFooter(false);
                adapter.onLoadMoreSuccess();
                adapter.addTail(list);
                break;
            case InitDataType.TYPE_REFRESH_FAIL:
                if (adapter.getItemCount() == 0) {
                    //显示空布局
                    adapter.onShowEmptyView(true, msg);
                }
                break;
            case InitDataType.TYPE_LOAD_MORE_FAIL:
                adapter.onLoadMoreFail(msg);
                break;
        }
    }

    /**
     * 获取adapter
     */
    public abstract BaseRecyclerViewAdapter<V> getAdapter();

    /**
     * 获取RecycleView的LayoutManager
     */
    public abstract RecyclerView.LayoutManager getLayoutManager();

    public void setListener(List<V> list,int position){

    }

    private void initNewsList() {
        adapter = getAdapter();

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) mRecyclerView.getAdapter();
                setListener(adapter.getAdapterData(),position);
            }
        });

        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (mPresenter != null)
                    mPresenter.loadMoreData();
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.addItemDecoration(new BaseSpaceItemDecoration(MeasureUtil.dip2px(getActivity(), 4)));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(adapter);
    }
}
