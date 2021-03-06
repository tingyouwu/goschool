package com.wty.app.goschool.mvp.model;

import com.wty.app.goschool.data.dalex.bmob.PublishDynamicBmob;
import com.wty.app.goschool.data.dalex.local.PublishDynamicDALEx;
import com.wty.app.goschool.mvp.model.impl.IPublishModel;
import com.wty.app.library.callback.ICallBack;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author wty
 */
public class PublishModel implements IPublishModel{

    @Override
    public void submit(final PublishDynamicDALEx data, final ICallBack<String> callBack) {
        PublishDynamicBmob bmob = new PublishDynamicBmob();
        //Bmob数据模型
        bmob.setAnnotationField(data);
        bmob.save(new SaveListener<String>() {
            @Override
            public void done(String objectid, BmobException e) {
                if(e != null){
                    callBack.onFaild(e.getMessage());
                }else{
                    data.setGsdynamicid(objectid);
                    data.saveOrUpdate();
                    callBack.onSuccess(objectid);
                }
            }
        });
    }
}
