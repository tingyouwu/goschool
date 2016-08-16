package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.mvp.view.IBaseView;

import java.util.List;

/**
 * @author wty
 */
public interface IComplainView extends IBaseView{

    boolean checkNet();

    void showNoNet();

    void refreshMore(List<PublishDynamicDALEx> list);

    void loadMore(List<PublishDynamicDALEx> list);

    void onRefreshComplete();

    void onRefreshComplete(String result);

    void onLoadMoreComplete();
}
