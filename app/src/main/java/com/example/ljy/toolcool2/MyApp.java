package com.example.ljy.toolcool2;

import com.xinbo.app.BaseApp;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by LJY on 2016/11/26.
 */

public class MyApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
    }
}
