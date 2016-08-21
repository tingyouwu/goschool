package com.wty.app.goschool.mvp.model.impl;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.library.mvp.model.IBaseModel;
import com.wty.app.library.mvp.presenter.ICallBack;

import java.util.List;

/**
 * @author wty
 */
public interface IMarketModel extends IBaseModel{
    /**
     * 加载吐槽
     **/
    void loadMoreComplain(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);

    /**
     * 刷新吐槽
     **/
    void refreshMoreComplain(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);
}
