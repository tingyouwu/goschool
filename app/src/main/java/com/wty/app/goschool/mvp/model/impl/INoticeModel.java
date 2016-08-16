package com.wty.app.goschool.mvp.model.impl;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.mvp.model.IBaseModel;
import com.wty.app.library.mvp.presenter.ICallBack;

import java.util.List;

/**
 * @author wty
 */
public interface INoticeModel extends IBaseModel{
    /**
     * 加载吐槽
     **/
    void loadMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack);

    /**
     * 刷新吐槽
     **/
    void refreshMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack);
}
