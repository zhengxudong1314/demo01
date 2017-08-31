package com.bwie.myxutils.application;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 1、类的用途：
 * 2、@author 郑旭东
 * 3、@date 2017/8/31
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public static DbManager getDbManager(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName("student.db").setDbVersion(1)
                .setDbDir(new File("/mnt/sdcard/"));
        DbManager db = x.getDb(daoConfig);
        return db;
    }
}
