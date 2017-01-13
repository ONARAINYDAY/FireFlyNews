package com.zjk.fireflynews.module.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.base.BaseRecyclerViewHolder;
import com.zjk.fireflynews.data.news.NewsListData;

import java.util.List;

/**
 * Created by FireFly on 2016/9/28 13:37.
 */
public class NewsListAdapter extends BaseRecyclerViewAdapter<NewsListData> {

    public NewsListAdapter(Context context, List<NewsListData> data) {
        super(context, data);
    }

    @Override
    public int bindItemViewLayout(int viewType) {
        return R.layout.new_list_item_layout;
    }

    @Override
    public void bindItemViewData(BaseRecyclerViewHolder holder, int position, NewsListData itemData) {
        Glide.with(mContext).load(itemData.getImgsrc()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail).into(holder.getImageView(R.id.iv_news_summary_photo));
        holder.setText(R.id.tv_news_summary_title, itemData.getTitle());
        holder.setText(R.id.tv_news_summary_digest, itemData.getDigest());
        holder.setText(R.id.tv_news_summary_ptime, itemData.getPtime());
    }
}
