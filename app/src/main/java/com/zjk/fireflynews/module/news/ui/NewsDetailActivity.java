package com.zjk.fireflynews.module.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseActivity;
import com.zjk.fireflynews.data.news.NewsDetailData;
import com.zjk.fireflynews.module.news.presenter.NewsDetPresenter;
import com.zjk.fireflynews.module.news.presenter.NewsDetPresenterImp;
import com.zjk.fireflynews.module.news.view.NewsDetActView;
import com.zzhoujay.richtext.RichText;

import butterknife.BindView;

/**
 * Created by FireFly on 2017/1/12 10:21.
 */
public class NewsDetailActivity extends BaseActivity<NewsDetPresenter> implements NewsDetActView {
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
    @BindView(R.id.tv_news_detail_from)
    TextView from;
    @BindView(R.id.tv_news_detail_body)
    TextView body;

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
        String postId = getIntent().getStringExtra(EXTRA_POST_ID);
        Glide.with(this).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).into(source);
        if (!TextUtils.isEmpty(postId))
            mPresenter = new NewsDetPresenterImp(this, postId);
    }

    @Override
    public void initUi(NewsDetailData newsDetailData) {
        title.setText(newsDetailData.getTitle());
        from.setText(getString(R.string.str_news_detail_from, newsDetailData.getSource(), newsDetailData.getPtime()));
        body.setText(Html.fromHtml(newsDetailData.getBody()));
    }

}
