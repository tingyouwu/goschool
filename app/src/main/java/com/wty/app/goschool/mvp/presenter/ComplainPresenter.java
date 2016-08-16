package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.ComplainModel;
import com.wty.app.goschool.mvp.model.impl.IComplainModel;
import com.wty.app.goschool.mvp.view.impl.IComplainView;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.mvp.presenter.ICallBack;

import java.util.List;

/**
 * @author wty
 */
public class ComplainPresenter extends BasePresenter<IComplainView>{

    private IComplainModel mComplainModel;

    public ComplainPresenter(){
        mComplainModel = new ComplainModel();
    }

    public void refreshMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onRefreshComplete();
            return;
        }

        mComplainModel.refreshMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
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

        mComplainModel.loadMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
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
