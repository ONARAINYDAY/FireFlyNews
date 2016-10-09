package com.zjk.fireflynews.module.video.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ImageView;

import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.base.BaseActivity;
import com.zjk.fireflynews.data.C;
import com.zjk.fireflynews.data.VideoListData;


public class PlayVideoActivity extends BaseActivity {

    private PlayerView player;
    private VideoListData mVideoListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        mVideoListData = (VideoListData) bundle.getSerializable(C.EXTRA_URL_KEY);
        setContentView(R.layout.activity_play_video);
        player = new PlayerView(this);
        player.setTitle(mVideoListData.getTitle()).setScaleType(PlayStateParams.fillparent).forbidTouch(false)
                .hideMenu(true).showThumbnail(new OnShowThumbnailListener() {
            @Override
            public void onShowThumbnail(ImageView ivThumbnail) {
                //播放前的预览图
            }
        }).setPlaySource(mVideoListData.getMp4_url()).setPlayerBackListener(new OnPlayerBackListener() {
            @Override
            public void onPlayerBack() {
                //这里可以监听播放器点击返回键
                finish();
            }
        });
        player.setOnlyFullScreen(true);
        player.hideRotation(true);
        player.startPlay();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (null != player) {
            player.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != player) {
            player.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (null != player && player.onBackPressed()) {
            return;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null != player) {
            player.onConfigurationChanged(newConfig);
        }
    }
}
