package com.example.ljy.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;


/**
 * Created by LJY on 2016/11/20.
 */

public class DayNightMode {
    /**设置日夜间模式
     * @param activity
     * @param isNight
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void setDayNightMode(AppCompatActivity activity, boolean isNight){
        if(isNight){
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            Toast.makeText(activity,"夜间模式",Toast.LENGTH_SHORT).show();
        }else{
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(activity,"日间模式",Toast.LENGTH_SHORT).show();
        }
        SPUtils.saveDayNightMode(activity,isNight);
        activity.recreate();
    }
    /**初始化日夜间模式
     * @param activity
     */
    public static void initDayNightMode(AppCompatActivity activity){
        boolean isNight = SPUtils.isNightMode(activity);
        if (isNight)
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}
