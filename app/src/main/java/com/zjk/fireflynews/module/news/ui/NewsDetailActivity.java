package com.zjk.fireflynews.module.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by FireFly on 2017/1/12 10:21.
 */
public class NewsDetailActivity extends BaseActivity {
    public static final String EXTRA_POST_ID = "extra_post_id";
    public static final String EXTRA_URL = "extra_url";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.backdrop)
    ImageView source;
    @BindView(R.id.news_detail_title)
    TextView title;

    public static void startNewsDetailInfo(Activity activity, String postID, String url) {
        Intent intent = new Intent(activity, NewsDetailActivity.class);
        intent.putExtra(EXTRA_POST_ID, postID);
        intent.putExtra(EXTRA_URL, url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(getString(R.string.str_news_detail));
        String url = getIntent().getStringExtra(EXTRA_URL);
        Glide.with(this).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).into(source);

    }
}
