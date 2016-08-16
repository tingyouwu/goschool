package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.library.mvp.view.IBaseView;
import com.wty.app.library.widget.sweetdialog.OnDismissCallbackListener;

import java.util.List;

/**
 * @author wty
 */
public interface IComplainView extends IBaseView{

    /**
     * 界面提示
     **/
    void showLoading(String loadmsg);
    void dismissLoading(OnDismissCallbackListener callback);

    /**
     * 网络检查提示
     **/
    boolean checkNet();

    /**
     * 显示无网络
     **/
    void showNoNet();

    void setAdapter(List<PublishDynamicDALEx> list);

    void loadMore(List<PublishDynamicDALEx> list);

    void onRefreshComplete();

    void onLoadMoreComplete();
}
