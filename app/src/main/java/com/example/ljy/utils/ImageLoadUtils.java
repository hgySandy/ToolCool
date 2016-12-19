package com.example.ljy.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.ConnectionUtils;
import com.xinbo.utils.UILUtils;

import java.io.File;

public class ImageLoadUtils {

	/**
	 * 下载条件1.有缓存2.仅wifi模式下下载并且有wifi3.非仅wifi 下载模式
	 * 
	 * @param imgview
	 * @param imgurl
	 * @param isOnlyWifiLoad
	 * 
	 */
	public static void displayImage(Activity activity, String imgurl, ImageView imgview) {
		File file = ImageLoader.getInstance().getDiskCache().get(imgurl);
		boolean hascache = (file != null);
		boolean isWifi = ConnectionUtils.isWIFI(activity);
		// boolean isWifi = false;
		boolean isOnlyWifi = SPUtils.isOnlyWifiImageLoadMode(activity);
//		Log.e("hascache", "" + hascache);
//		Log.e("isWifi", "" + isWifi);
//		Log.e("isOnlyWifi", "" + isOnlyWifi);
		boolean result = false;
		if (hascache || (isOnlyWifi && isWifi) || !isOnlyWifi) {
			result = hascache || (isOnlyWifi && isWifi) || !isOnlyWifi;
//			Log.e("result", "" + result);
			UILUtils.displayImageNoAnim(imgurl, imgview);
		}
	}

	public static void displayCircleImage(Activity activity, String imgurl, ImageView imgview, boolean isOnlyWifiLoad) {
		File file = ImageLoader.getInstance().getDiskCache().get(imgurl);
		boolean hascache = file != null;
		boolean isWifi = ConnectionUtils.isWIFI(activity);
		boolean isOnlyWifi = SPUtils.isOnlyWifiImageLoadMode(activity);
		if (hascache || (isOnlyWifi && isWifi) || !isWifi)
			UILUtils.displayCircleImage(imgurl, imgview);
	}

}
