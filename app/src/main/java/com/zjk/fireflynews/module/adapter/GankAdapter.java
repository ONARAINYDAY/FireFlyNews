package com.zjk.fireflynews.module.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.base.BaseRecyclerViewHolder;
import com.zjk.fireflynews.data.gank.GankItem;
import com.zjk.fireflynews.data.gank.GankTitle;
import com.zjk.fireflynews.utils.C;

import java.util.List;

/**
 * Created by FireFly on 2017/2/8 10:33.
 */
public class GankAdapter extends BaseRecyclerViewAdapter<GankItem> {

    public GankAdapter(Context context, List<GankItem> data) {
        super(context, data);
    }

    @Override
    public int bindItemViewLayout(int viewType) {
        if (viewType == C.TYPE_GIRL) {
            return R.layout.gank_list_item_layout;
        } else if (viewType == C.TYPE_TITLE) {
            return R.layout.gank_add_layout;
        } else if (viewType == C.TYPE_DESC) {
            return R.layout.gank_add_content_layout;
        }
        return 0;
    }

    @Override
    public int getEveryItemViewType(int position) {
        GankItem gankItem = mData.get(position);
        return gankItem.getViewType();
    }

    @Override
    public void bindItemViewData(BaseRecyclerViewHolder holder, int position, GankItem itemData) {
        if (getEveryItemViewType(position) == C.TYPE_GIRL) {
            Glide.with(mContext).load(itemData.getUrl()).asBitmap().placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail).format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.getImageView(R.id.gank_girl));
            holder.setText(R.id.tv_time, itemData.getPublishedAt().split("T")[0]);
        } else if (getEveryItemViewType(position) == C.TYPE_TITLE) {
            holder.setText(R.id.tv_title, ((GankTitle) itemData).getTitle());
        } else if (getEveryItemViewType(position) == C.TYPE_DESC) {
            if (TextUtils.isEmpty(itemData.getWho())) {
                holder.setText(R.id.tv_desc, itemData.getWho());
            } else {
                int start = itemData.getDesc().length();
                int end = start + itemData.getWho().length() + 3;
                int color = Color.parseColor("#bdbdbd");
                SpannableStringBuilder span = new SpannableStringBuilder(itemData.getDesc() + " - " + itemData.getWho());
                span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                span.setSpan(new RelativeSizeSpan(0.85f), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ((TextView) holder.getView(R.id.tv_desc)).setText(span);
            }
        }
    }
}
