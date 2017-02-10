package com.zjk.fireflynews;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zjk.fireflynews.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.iv_photo)
    PhotoView photoView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        Glide.with(this).load(url).asBitmap().placeholder(R.drawable.ic_loading).error(R.drawable.ic_fail).format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.RESULT).into(photoView);
    }
}
