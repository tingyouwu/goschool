package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.model.HotModel;
import com.wty.app.goschool.mvp.model.impl.IHotModel;
import com.wty.app.goschool.mvp.view.impl.IHotView;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author wty
 */
public class HotPresenter extends BasePresenter<IHotView>{

    private IHotModel mHotModel;

    public HotPresenter(){
        mHotModel = new HotModel();
    }

    public void refreshMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onRefreshComplete();
            return;
        }

        mHotModel.refreshMoreHot(new MarketDynamicDALEx(), new ICallBack<List<MarketDynamicDALEx>>() {
            @Override
            public void onSuccess(List<MarketDynamicDALEx> data) {
                mView.onRefreshComplete(data.size());
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

        mHotModel.loadMoreHot(new MarketDynamicDALEx(), new ICallBack<List<MarketDynamicDALEx>>() {
            @Override
            public void onSuccess(List<MarketDynamicDALEx> data) {
                mView.onLoadMoreComplete(data.size());
                mView.loadMore(data);
            }

            @Override
            public void onFaild(String msg) {

            }
        });
    }

    public void loadHotFirst(){
        mView.showNoNet();
    }

}
