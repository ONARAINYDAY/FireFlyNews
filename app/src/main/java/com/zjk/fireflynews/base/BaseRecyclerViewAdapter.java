package com.zjk.fireflynews.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjk.fireflynews.R;
import com.zjk.fireflynews.callback.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FireFly on 2016/9/28 13:16.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected static final int TYPE_DEFAULT = 0;
    protected static final int TYPE_LOAD_MORE_SUCCESS = TYPE_DEFAULT + 1;
    protected static final int TYPE_LOAD_MORE_FAIL = TYPE_LOAD_MORE_SUCCESS + 1;


    private String mRetryHintMsg;

    protected List<T> mData;
    protected Context mContext;
    protected boolean mUseAnimation;
    protected LayoutInflater mInflater;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isShowFooter;
    private boolean mLoadMoreEnable;
    private OnLoadMoreListener mOnLoadMoreListener;

    public BaseRecyclerViewAdapter(Context context, List<T> data) {
        this(context, data, true);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> data, boolean useAnimation) {
        this(context, data, useAnimation, null);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> data, boolean useAnimation, RecyclerView.LayoutManager layoutManager) {
        mContext = context;
        mUseAnimation = useAnimation;
        mLayoutManager = layoutManager;
        mData = data == null ? new ArrayList<T>() : data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder holder = null;
        if (viewType == TYPE_LOAD_MORE_FAIL) {
            holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.footer_load_more_fail, parent, false));
            holder.getTextView(R.id.footer_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnLoadMoreListener == null) return;
                    mLoadMoreEnable = true;
                    isShowFooter = true;
                    notifyItemChanged(getItemCount() - 1);
                    v.post(new Runnable() {
                        @Override
                        public void run() {
                            mOnLoadMoreListener.onLoadMore();
                        }
                    });
                }
            });
        } else if (viewType == TYPE_LOAD_MORE_SUCCESS) {
            holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(R.layout.footer_load_more, parent, false));
        } else {
            holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(bindItemViewLayout(0), parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_LOAD_MORE_SUCCESS) {

        } else if (getItemViewType(position) == TYPE_LOAD_MORE_FAIL) {
            holder.setText(R.id.footer_retry_msg, mRetryHintMsg);
        } else {
            bindItemViewData(holder, position, mData.get(position));
        }

        if (mOnLoadMoreListener != null && mLoadMoreEnable && !isShowFooter && position == getItemCount() - 1) {
            isShowFooter = true;
            holder.itemView.post(new Runnable() {
                @Override
                public void run() {
                    notifyItemInserted(getItemCount());
                    mOnLoadMoreListener.onLoadMore();
                }
            });
        }

    }

    /**
     * 布局文件资源
     */
    public abstract int bindItemViewLayout(int viewType);

    /**
     * 加载每一个item的数据
     */
    public abstract void bindItemViewData(BaseRecyclerViewHolder holder, int position, T itemData);

    @Override
    public int getItemCount() {//1、显示加载中，enable为true  2、加载中未显示，enable为false，显示加载失败
        int hasFooter = mOnLoadMoreListener == null ? 0 : isShowFooter && mLoadMoreEnable || !isShowFooter && !mLoadMoreEnable ? 1 : 0;
        return mData == null ? 0 : mData.size() + hasFooter;
    }

//    public void setShowFooter(boolean isShowFooter) {
//        this.isShowFooter = isShowFooter;
//        if (isShowFooter) {
//            notifyItemInserted(getItemCount());
//        } else {
//            notifyItemRemoved(getItemCount());
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        if (mOnLoadMoreListener != null && mLoadMoreEnable && isShowFooter && getItemCount() - 1 == position) {
            return TYPE_LOAD_MORE_SUCCESS;
        } else if (mOnLoadMoreListener != null && !mLoadMoreEnable && !isShowFooter && getItemCount() - 1 == position) {
            return TYPE_LOAD_MORE_FAIL;
        }
        return TYPE_DEFAULT;
    }

    /**
     * 下拉刷新，更新数据
     */
    public void update(List<T> data) {
        if (data == null || data.isEmpty()) return;
        mData.clear();
        mData = data;
        notifyDataSetChanged();
    }

    /**
     * 上拉加载，添加数据
     */
    public void addTail(List<T> data) {
        if (data == null || data.isEmpty()) return;
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        mLoadMoreEnable = true;
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void onLoadMoreSuccess() {
        mLoadMoreEnable = true;
        isShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    public void onLoadMoreFail(String msg) {
        mLoadMoreEnable = false;
        isShowFooter = false;
        mRetryHintMsg = msg;
        notifyItemChanged(getItemCount() - 1);
    }

}
