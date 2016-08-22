package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.PictureModel;
import com.wty.app.goschool.mvp.model.impl.IPictureModel;
import com.wty.app.goschool.mvp.view.impl.INoticeView;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.presenter.BasePresenter;

import java.util.List;

/**
 * @author wty
 */
public class PicturePresenter extends BasePresenter<INoticeView>{

    private IPictureModel mPictureModel;

    public PicturePresenter(){
        mPictureModel = new PictureModel();
    }

    public void refreshMoreComplain(){
        if(!mView.checkNet()){
            mView.showNoNet();
            mView.onRefreshComplete();
            return;
        }

        mPictureModel.refreshMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
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

        mPictureModel.loadMoreComplain(new PublishDynamicDALEx(), new ICallBack<List<PublishDynamicDALEx>>() {
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
