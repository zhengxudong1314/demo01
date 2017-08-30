package com.bwie.xutils;

import android.app.Application;

import org.xutils.x;

/**
 * 1、类的用途：
 * 2、@author 郑旭东
 * 3、@date 2017/8/29
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //输出debug日志，开启会影响性能
        x.Ext.setDebug(true);
    }
}
