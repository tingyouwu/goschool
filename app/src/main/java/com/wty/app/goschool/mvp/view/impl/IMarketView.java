package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.mvp.view.IBaseView;

import java.util.List;

/**
 * @author wty
 */
public interface IMarketView extends IBaseView{

    boolean checkNet();

    void showNoNet();

    void refreshMore(List<MarketDynamicDALEx> list);

    void loadMore(List<MarketDynamicDALEx> list);

    void onRefreshComplete();

    void onRefreshComplete(String result);

    void onLoadMoreComplete();

    void onLoadMoreComplete(String result);
}
