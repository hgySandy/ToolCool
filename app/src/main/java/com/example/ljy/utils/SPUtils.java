package com.example.ljy.utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class SPUtils {


	/**
	 * @param activity
	 * @param size 0-4对应五档字号
 	 */
	public static void saveTextSize(Activity activity,int size){
		SharedPreferences myConfig=activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME,activity.MODE_PRIVATE);
		myConfig.edit().putInt("textSize",size).commit();
	}

    /**
     * @param activity
     * @return 返回字号 默认为2档
     */
    public static int getTextSize(Activity activity){
		SharedPreferences myConfig=activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME,activity.MODE_PRIVATE);
        int textSize = myConfig.getInt("textSize", 2);
        return textSize;
	}

	/**保存日夜间模式到本地
	 * @param activity
	 * @param isNight
	 */
	public static void saveDayNightMode(Activity activity,boolean isNight){
		SharedPreferences myConfig = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		myConfig.edit().putBoolean("isNightMode",isNight).commit();
	}

	/**
	 * @param activity
	 * @return 获取保存在本地的日夜间模式
	 */
	public static boolean isNightMode(Activity activity){
		SharedPreferences myconfig=activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME,activity.MODE_PRIVATE);
		boolean isNightMode = myconfig.getBoolean("isNightMode", false);
		return isNightMode;
	}

	/**保存登录图片的url到本地
	 * @param activity
	 * @param imgurl
	 */
	public static void saveLoginImageUrl(Activity activity,String imgurl){
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		sp.edit().putString("loginImgUrl",imgurl).commit();
	}
	public static String getLoginImageUrl(Activity activity){
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		String loginImgUrl = sp.getString("loginImgUrl", null);
		return loginImgUrl;
	}





	public static boolean isOnlyWifiImageLoadMode(Activity activity) {
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		return sp.getBoolean("isonlywifi", false);
	}

	public static void saveWifiImageLoadMode(Activity activity, boolean isLoadOnWifi) {
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		sp.edit().putBoolean("isonlywifi", isLoadOnWifi).commit();
	}
	public static void setNoFistToDetail(Activity activity){
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		sp.edit().putBoolean("isfirstToDetail", false).commit();
	}
	public static boolean isFirstToDetail(Activity activity){
		SharedPreferences sp = activity.getSharedPreferences(TKContants.Name.SP_FILE_NAME, activity.MODE_PRIVATE);
		boolean isfirst = sp.getBoolean("isfirstToDetail", true);
		return isfirst;
	}



}
