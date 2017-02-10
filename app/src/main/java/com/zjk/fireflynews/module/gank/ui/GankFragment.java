package com.zjk.fireflynews.module.gank.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zjk.fireflynews.PhotoViewActivity;
import com.zjk.fireflynews.R;
import com.zjk.fireflynews.WebViewActivity;
import com.zjk.fireflynews.base.BaseRecyclerViewAdapter;
import com.zjk.fireflynews.data.gank.GankData;
import com.zjk.fireflynews.data.gank.GankItem;
import com.zjk.fireflynews.data.gank.GankItemDesc;
import com.zjk.fireflynews.module.adapter.GankAdapter;
import com.zjk.fireflynews.module.base.ui.BaseListFragment;
import com.zjk.fireflynews.module.gank.presenter.GankPresenter;
import com.zjk.fireflynews.module.gank.presenter.GankPresenterImp;
import com.zjk.fireflynews.module.gank.view.GankView;
import com.zjk.fireflynews.utils.C;

import java.util.List;


/**
 * Created by FireFly on 2016/9/30 13:49.
 */
public class GankFragment extends BaseListFragment<GankPresenter, GankItem> implements GankView {
    @Override
    public void initView(View view) {
        mPresenter = new GankPresenterImp(this);
        mPresenter.onRefreshData();
    }

    @Override
    public BaseRecyclerViewAdapter<GankItem> getAdapter() {
        return new GankAdapter(getActivity(), null);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public void setListener(View v, List<GankItem> list, int position) {
        GankItem gankItem = list.get(position);
        switch (gankItem.getViewType()) {
            case C.TYPE_DESC:
                if (!TextUtils.isEmpty(gankItem.getUrl())) {
                    WebViewActivity.startActivity(getActivity(), gankItem.getUrl());
                }
                break;
            case C.TYPE_GIRL:
                if (Build.VERSION.SDK_INT < 21) {
                    toast("21+ only, keep out");
                } else {
                    if (TextUtils.isEmpty(list.get(position).getUrl())) {
                        return;
                    }
                    Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, list.get(position).getUrl());
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), v.findViewById(R.id.gank_girl), getString(R.string.transition_pic));
                    startActivity(intent, options.toBundle());
                }
                break;
            default:
                break;
        }
    }
}
