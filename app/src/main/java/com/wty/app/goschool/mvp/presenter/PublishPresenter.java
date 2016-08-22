package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.PublishModel;
import com.wty.app.goschool.mvp.model.impl.IPublishModel;
import com.wty.app.goschool.mvp.view.impl.IPublishView;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.widget.sweetdialog.OnDismissCallbackListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author wty
 */
public class PublishPresenter extends BasePresenter<IPublishView>{

    private IPublishModel mPublishModel;

    public PublishPresenter(){
        mPublishModel = new PublishModel();
    }

    public void submit(final PublishDynamicDALEx data){
        if(!mView.checkNet()){
            mView.showNoNet();
            return;
        }
        mView.showLoading("请稍候，正在提交数据...");
        mPublishModel.submit(data, new ICallBack<String>() {
            @Override
            public void onSuccess(String objectid) {
                mView.dismissLoading(new OnDismissCallbackListener("提交成功"){
                    @Override
                    public void onCallback() {
                        mView.finishActivity();
                    }
                });
            }

            @Override
            public void onFaild(String error) {
                mView.dismissLoading(new OnDismissCallbackListener(error, SweetAlertDialog.ERROR_TYPE));
            }
        });

    }

}
