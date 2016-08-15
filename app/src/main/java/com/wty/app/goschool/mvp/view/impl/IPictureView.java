package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.mvp.view.IBaseView;

import java.util.List;

/**
 * @author wty
 */
public interface IPictureView extends IBaseView{
    void setAdapter(List<PublishDynamicDALEx> list);

    //加载更多
    void loadMore(List<PublishDynamicDALEx> list);

    //下拉刷新完毕
    void onRefreshComplete();

    //加载更多完毕
    void onLoadMoreComplete();

    //加载成功
    void showSuccess();

    //显示空白
    void showEmpty();

    //检查网络
    boolean checkNet();

    //显示失败
    void showFaild();

    //显示无网络
    void showNoNet();
}
