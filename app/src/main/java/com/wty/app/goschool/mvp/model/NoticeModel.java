package com.wty.app.goschool.mvp.model;

import com.wty.app.goschool.data.dalex.bmob.MarketDynamicBmob;
import com.wty.app.goschool.data.dalex.local.MarketDynamicDALEx;
import com.wty.app.goschool.mvp.model.impl.INoticeModel;
import com.wty.app.library.callback.ICallBack;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @author wty
 */
public class NoticeModel implements INoticeModel{


    @Override
    public void loadMoreNotice(MarketDynamicDALEx data, ICallBack<List<MarketDynamicDALEx>> callBack) {
        List<MarketDynamicDALEx> list = new ArrayList<MarketDynamicDALEx>();
        callBack.onSuccess(list);
    }

    @Override
    public void refreshMoreNotice(MarketDynamicDALEx data, final ICallBack<List<MarketDynamicDALEx>> callBack) {
        BmobQuery<MarketDynamicBmob> query = new BmobQuery<MarketDynamicBmob>();

        query.findObjects(new FindListener<MarketDynamicBmob>() {
            @Override
            public void done(List<MarketDynamicBmob> list, BmobException e) {
                if(e != null){
                    callBack.onFaild(e.getMessage());
                }else{
                    List<MarketDynamicDALEx> newlist = MarketDynamicBmob.get().saveReturn(list);
                    callBack.onSuccess(newlist);
                }
            }
        });
    }

    @Override
    public void loadNoticeFirst(ICallBack<List<MarketDynamicDALEx>> callBack) {
        List<MarketDynamicDALEx> list = new ArrayList<MarketDynamicDALEx>();
        callBack.onSuccess(list);
    }
}
