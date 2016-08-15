package com.wty.app.goschool.mvp.presenter;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.PublishModel;
import com.wty.app.goschool.mvp.model.impl.IPublishModel;
import com.wty.app.goschool.mvp.view.impl.IPublishView;
import com.wty.app.library.mvp.presenter.BasePresenter;

/**
 * @author wty
 */
public class PublishPresenter extends BasePresenter<IPublishView>{

    private IPublishModel model;

    public PublishPresenter(){
        model = new PublishModel();
    }

    public void submit(PublishDynamicDALEx data){
        if(!mView.checkNet()){
            mView.showNoNet();
            return;
        }

        model.submit(data);

    }

}
