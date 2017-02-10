package com.zjk.fireflynews.data.gank;


import com.zjk.fireflynews.utils.C;

/**
 * Created by FireFly on 2017/2/8 17:19.
 */
public class GankTitle extends GankItem {

    private String title;

    public GankTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getViewType() {
        return C.TYPE_TITLE;
    }
}
