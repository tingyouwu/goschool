package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.HelpModel;
import com.wty.app.goschool.mvp.model.impl.IHelpModel;
import com.wty.app.goschool.mvp.view.impl.IHelpView;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author wty
 */
public class HelpPresenter extends BasePresenter<IHelpView>{

    private IHelpModel mHelpModel;

    public HelpPresenter(){
        mHelpModel = new HelpModel();
    }

    public void refreshMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onRefreshComplete();
            return;
        }

        mHelpModel.refreshMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
            @Override
            public void onSuccess(List<PublishDynamicDALEx> data) {
                mView.onRefreshComplete(data.size()+"条新内容");
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

        mHelpModel.loadMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
            @Override
            public void onSuccess(List<PublishDynamicDALEx> data) {
                mView.onLoadMoreComplete();
                mView.loadMore(data);
            }

            @Override
            public void onFaild(String msg) {

            }
        });
    }

}
