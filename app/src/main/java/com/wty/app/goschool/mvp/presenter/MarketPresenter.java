package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.model.MarketModel;
import com.wty.app.goschool.mvp.model.impl.IMarketModel;
import com.wty.app.goschool.mvp.view.impl.IMarketView;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author wty
 */
public class MarketPresenter extends BasePresenter<IMarketView>{

    private IMarketModel mMarketModel;

    public MarketPresenter(){
        mMarketModel = new MarketModel();
    }

    public void refreshMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onRefreshComplete();
            return;
        }

        mMarketModel.refreshMoreMarket(new MarketDynamicDALEx(), new ICallBack<List<MarketDynamicDALEx>>() {
            @Override
            public void onSuccess(List<MarketDynamicDALEx> data) {
                mView.onRefreshComplete(data.size() + "条新内容");
                mView.refreshMore(data);
            }

            @Override
            public void onFaild(String msg) {

            }
        });

    }

    public void loadMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onLoadMoreComplete();
            return;
        }

        mMarketModel.loadMoreMarket(new MarketDynamicDALEx(), new ICallBack<List<MarketDynamicDALEx>>() {
            @Override
            public void onSuccess(List<MarketDynamicDALEx> data) {
                mView.onLoadMoreComplete();
                mView.loadMore(data);
            }

            @Override
            public void onFaild(String msg) {

            }
        });
    }

    public void loadMarketFirst(){

    }

}
