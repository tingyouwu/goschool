package com.wty.app.goschool.mvp.model.impl;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.model.IBaseModel;

/**
 * @author wty
 */
public interface IPublishModel extends IBaseModel{
    /**
     * @Decription 提交数据
     **/
    void submit(PublishDynamicDALEx data, ICallBack<String> callBack);
}
