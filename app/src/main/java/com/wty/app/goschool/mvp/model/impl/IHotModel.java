package com.wty.app.goschool.mvp.model.impl;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.model.IBaseModel;

import java.util.List;

/**
 * @author wty
 */
public interface IHotModel extends IBaseModel{
    /**
     * 加载更多
     **/
    void loadMoreHot(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);

    /**
     * 刷新更多
     **/
    void refreshMoreHot(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);

    /**
     * 首次进入加载数据
     **/
    void loadHotFirst(ICallBack<List<MarketDynamicDALEx>> callBack);
}
