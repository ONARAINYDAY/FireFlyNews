package com.zjk.fireflynews.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by FireFly on 2016/9/28 10:47.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    //集合类，layout里包含的View,以view的id作为key，value是view对象
    protected SparseArray<View> mViews;
    //上下文对象
    protected Context mContext;

    public BaseRecyclerViewHolder(Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        mViews = new SparseArray<>();
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    public ImageView getImageView(int viewId){
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId){
        return findViewById(viewId);
    }

    /****以下为辅助方法*****/

    public BaseRecyclerViewHolder setText(int viewId, String text) {
        TextView tv = findViewById(viewId);
        tv.setText(text);
        return this;
    }
}
