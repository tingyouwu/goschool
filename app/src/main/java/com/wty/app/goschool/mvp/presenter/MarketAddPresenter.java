package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.model.MarketAddModel;
import com.wty.app.goschool.mvp.model.impl.IMarketAddModel;
import com.wty.app.goschool.mvp.view.impl.IMarketAddView;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.mvp.presenter.ICallBack;
import com.wty.app.library.widget.sweetdialog.OnDismissCallbackListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @author wty
 * @Decription 跳蚤市场发布消息 presenter
 */
public class MarketAddPresenter extends BasePresenter<IMarketAddView>{

    private IMarketAddModel mMarketAddModel;

    public MarketAddPresenter(){
        mMarketAddModel = new MarketAddModel();
    }

    public void submit(final MarketDynamicDALEx data){
        if(!mView.checkNet()){
            mView.showNoNet();
            return;
        }
        mView.showLoading("请稍候，正在发布...");
        mMarketAddModel.submit(data, new ICallBack<String>() {
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
