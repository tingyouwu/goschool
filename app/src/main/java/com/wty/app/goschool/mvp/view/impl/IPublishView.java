package com.wty.app.goschool.mvp.view.impl;

import com.wty.app.library.mvp.view.IBaseView;

/**
 * @author wty
 */
public interface IPublishView extends IBaseView{

    /**
     * 提交数据过程的界面提示
     **/
    void showSuccess();

    void showFaild();

    /**
     * 网络检查提示
     **/
    boolean checkNet();
    //显示无网络
    void showNoNet();
}
