package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.library.mvp.view.IBaseView;

import java.util.List;

/**
 * @author wty
 */
public interface IHotView extends IBaseView{

    boolean checkNet();

    void showNoNet();

    void refreshMore(List<MarketDynamicDALEx> list);

    void loadMore(List<MarketDynamicDALEx> list);

    void onRefreshComplete();

    /**
     * @param result 下拉刷新完毕后返回的数据条数
     **/
    void onRefreshComplete(int result);

    void onLoadMoreComplete();

    /**
     * @param result 上拉加载更多完毕后返回的数据条数
     **/
    void onLoadMoreComplete(int result);
}
