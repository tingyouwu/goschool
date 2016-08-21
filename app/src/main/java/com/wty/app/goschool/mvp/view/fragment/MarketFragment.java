package com.wty.app.goschool.mvp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wty.app.goschool.R;
import com.wty.app.goschool.adapter.MarketAdapter;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.presenter.MarketPresenter;
import com.wty.app.goschool.mvp.view.activity.MarketAddActivity;
import com.wty.app.goschool.mvp.view.impl.IMarketView;
import com.wty.app.library.adapter.BaseRecyclerViewAdapter;
import com.wty.app.library.fragment.BaseFragment;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.utils.NetWorkUtils;
import com.wty.app.library.widget.DivItemDecoration;
import com.wty.app.library.widget.loadingview.LoadingState;
import com.wty.app.library.widget.loadingview.LoadingView;
import com.wty.app.library.widget.xrecyclerview.ProgressStyle;
import com.wty.app.library.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 主页->寻宝贝（跳蚤市场）
 * @author wty
 */
public class MarketFragment extends BaseFragment implements IMarketView{

    BaseRecyclerViewAdapter adapter;
    private List<MarketDynamicDALEx> mDataList = new ArrayList<MarketDynamicDALEx>();

    @Bind(R.id.listview_life)
    XRecyclerView listview;
    @Bind(R.id.fl_loading)
    LoadingView mLoadingView;

    @Override
    public BasePresenter getPresenter() {
        return new MarketPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        adapter = new MarketAdapter(getContext(),mDataList);
        listview.setLayoutManager(new LinearLayoutManager(getActivity()));
        listview.setAdapter(adapter);
        listview.addItemDecoration(new DivItemDecoration(15, false));
        listview.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        listview.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse);
        listview.setLoadingMoreEnabled(false);

        listview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                ((MarketPresenter)mPresenter).refreshMoreComplain();
            }

            @Override
            public void onLoadMore() {
                ((MarketPresenter)mPresenter).loadMoreComplain();
            }

        });
        mLoadingView.build();

    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_life;
    }

    @Override
    public void initFragmentActionBar(String title) {
        super.initFragmentActionBar(title);
        activity.getDefaultNavigation().setRightButton("发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarketAddActivity.startMarketAddActivity(getContext());
            }
        });
    }

    @Override
    public boolean checkNet() {
        return NetWorkUtils.isNetworkConnected(getContext());
    }

    @Override
    public void showNoNet() {
    }

    @Override
    public void refreshMore(List<MarketDynamicDALEx> list) {
        adapter.addData(list);
    }

    @Override
    public void loadMore(List<MarketDynamicDALEx> list) {
        mDataList.addAll(list);
    }

    @Override
    public void onRefreshComplete() {
        listview.refreshComplete();
    }

    @Override
    public void onRefreshComplete(String result) {
        listview.refreshComplete(result);
    }

    @Override
    public void onLoadMoreComplete() {
        listview.loadMoreComplete();
    }

    @Override
    public void onLoadMoreComplete(String result) {
        listview.setNoMore(result);
    }
}
