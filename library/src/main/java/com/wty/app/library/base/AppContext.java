package com.wty.app.library.base;

import android.content.Context;

import java.io.Serializable;
import java.lang.ref.WeakReference;

public class AppContext implements Serializable {

	public static final String SD_PATH = "com.wty.app";
	public static final String PATH = android.os.Environment.getExternalStorageDirectory().getPath() + "/"+SD_PATH;
	public static final String SAVEIMAGEPATH = PATH + "/SaveImages";
	public static final String IMAGEPATH = PATH + "/Images";
	public static final String VOICEPATH = PATH  + "/voice";
	public static final String DOCPATH = PATH  + "/file";
	public static final String CAMERA_PATH = PATH + "/CameraImage/";
	public static final String CROP_PATH = PATH + "/CropImage/";

	private WeakReference<Context> context;
	private static AppContext instance = null;

	public static synchronized AppContext getInstance() {
		if (instance == null) {
			instance = new AppContext();
		}
		return instance;
	}

	/**
	 * 获取 系统上下文
	 */
	public static Context getContext() {
		if (getInstance().context == null) {
			return null;
		}
		return getInstance().context.get();
	}

	/**
	 * 设置 系统上下文
	 */
	public static void setContext(Context context) {
        if(getInstance().context!=null){
            getInstance().context.clear();
        }
		getInstance().context = new WeakReference<Context>(context);
	}

}
