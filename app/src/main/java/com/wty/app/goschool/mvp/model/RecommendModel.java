package com.wty.app.goschool.mvp.model;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.impl.IRecommendModel;
import com.wty.app.library.callback.ICallBack;

import java.util.List;

/**
 * @author wty
 */
public class RecommendModel implements IRecommendModel{

    @Override
    public void loadMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack) {

    }

    @Override
    public void refreshMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack) {

    }
}
