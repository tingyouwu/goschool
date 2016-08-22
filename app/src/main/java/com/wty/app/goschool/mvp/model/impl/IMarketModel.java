package com.wty.app.goschool.mvp.model.impl;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.library.callback.ICallBack;
import com.wty.app.library.mvp.model.IBaseModel;

import java.util.List;

/**
 * @author wty
 */
public interface IMarketModel extends IBaseModel{
    /**
     * 加载更多跳蚤信息
     **/
    void loadMoreMarket(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);

    /**
     * 刷新更多跳蚤信息
     **/
    void refreshMoreMarket(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack);

    /**
     * 首次进入加载数据
     **/
    void loadMarketFirst(ICallBack<List<MarketDynamicDALEx>> callBack);
}
