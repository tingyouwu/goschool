package com.wty.app.library.data;

import com.wty.app.library.data.impl.IDataModel;

public class DataManager {

    private static volatile DataManager sInstance = null;

    private IDataModel mIDataModel;

    private DataManager(){
    }

    public static DataManager getInstance() {
        if (sInstance == null) {
            synchronized (DataManager.class) {
                if (sInstance == null) {
                    sInstance = new DataManager();
                }
            }
        }
        return sInstance;
    }

}
