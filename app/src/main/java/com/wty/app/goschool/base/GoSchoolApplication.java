package com.wty.app.goschool.base;

import android.content.Context;

import com.wty.app.goschool.R;
import com.wty.app.library.base.AppConstant;
import com.wty.app.library.base.MainApplication;
import com.wty.app.library.widget.emptyview.LoadingAndRetryManager;

import cn.bmob.v3.Bmob;


public class GoSchoolApplication extends MainApplication {

	private static Context mApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this.getApplicationContext();
		//初始化Bmob功能
		Bmob.initialize(this, AppConstant.Bmob_ApplicationId);
		initLoadingView();
	}

	/**
	 * 功能描述：获得一个全局的application对象
	 **/
	public static Context getInstance(){
		return mApplication;
	}

	public void initLoadingView(){
		LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;
		LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
		LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
	}

}
