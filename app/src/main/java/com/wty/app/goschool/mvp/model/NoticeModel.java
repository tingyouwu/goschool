package com.wty.app.goschool.mvp.model;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.impl.INoticeModel;
import com.wty.app.library.callback.ICallBack;

import java.util.List;

/**
 * @author wty
 */
public class NoticeModel implements INoticeModel{

    @Override
    public void loadMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack) {

    }

    @Override
    public void refreshMoreComplain(PublishDynamicDALEx data, ICallBack<List<PublishDynamicDALEx>> callBack) {

    }
}
