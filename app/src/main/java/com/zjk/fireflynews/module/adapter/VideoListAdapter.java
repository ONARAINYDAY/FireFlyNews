package com.zjk.fireflynews.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.base.BaseRecyclerViewHolder;
import com.zjk.fireflynews.data.video.VideoListData;
import com.zjk.fireflynews.utils.MeasureUtil;

import java.util.List;
import java.util.Random;

/**
 * Created by FireFly on 2016/10/8 21:49.
 */
public class VideoListAdapter extends BaseRecyclerViewAdapter<VideoListData> {

    Random mRandom = new Random();

    public VideoListAdapter(Context context, List<VideoListData> data, RecyclerView.LayoutManager mLayoutManager) {
        super(context, data, mLayoutManager);
    }

    @Override
    public int bindItemViewLayout(int viewType) {
        return R.layout.video_list_item_layout;
    }

    @Override
    public int getEveryItemViewType(int position) {
        return TYPE_DEFAULT;
    }

    @Override
    public void bindItemViewData(BaseRecyclerViewHolder holder, int position, VideoListData itemData) {
        final ImageView imageView = holder.getImageView(R.id.iv_video_summary);
        final ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (itemData.getPicWidth() == -1 && itemData.getPicHeight() == -1) {
            itemData.setPicWidth(MeasureUtil.getScreenSize(mContext).x / 2 - MeasureUtil.dip2px(mContext, 4) * 2 - MeasureUtil.dip2px(mContext, 2));
            itemData.setPicHeight((int) (itemData.getPicWidth() * (mRandom.nextFloat() / 2 + 0.7)));
        }
        params.width = itemData.getPicWidth();
        params.height = itemData.getPicHeight();
        imageView.setLayoutParams(params);

        Glide.with(mContext).load(itemData.getCover()).asBitmap().placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail).format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);

        holder.getTextView(R.id.tv_video_summary).setText(Html.fromHtml(itemData.getTitle()));
    }
}
