package com.zjk.fireflynews.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by FireFly on 2016/9/29 13:24.
 */
@Deprecated
public class PullToRecyclerView extends RecyclerView {

    private boolean isLoadMoreEnabled = false;
    private OnLoadMordListener onLoadMordListener;

    public PullToRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        init();
    }

    public PullToRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullToRecyclerView(Context context) {
        this(context,null);
    }
    private void init(){
        this.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoadMoreEnabled && checkCanOnLoad()){
                    if(null!=onLoadMordListener)
                        onLoadMordListener.onLoadMore();
                }
            }
        });
    }

    private boolean checkCanOnLoad() {
        final LayoutManager manager = getLayoutManager();
        if(manager==null){
            throw new IllegalArgumentException("RecyclerView's LayoutManager is null");
        }
        if(manager instanceof LinearLayoutManager){
            final int total = manager.getItemCount();
            final int lastPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            return total - lastPosition < 3;
        }
        return false;
    }

    public void setLoadMoreEnabled(boolean loadMoreEnabled) {
        isLoadMoreEnabled = loadMoreEnabled;
    }

    public void setOnLoadMordListener(OnLoadMordListener onLoadMordListener) {
        this.onLoadMordListener = onLoadMordListener;
    }

    public interface OnLoadMordListener{
        void onLoadMore();
    }
}
