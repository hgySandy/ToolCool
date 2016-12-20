package com.example.ljy.utils;


import android.content.Context;

import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;

public class ApiClient
{
	//获取article详情
	public static void get(Context context, int position, ResponseListener listener)
	{
		HTTPUtils.get(context, TKContants.Url.CATEGORYS[position], listener);
	}
	
	public static void getWeekly(Context context, ResponseListener listener)
	{
		HTTPUtils.get(context, TKContants.Url.WEEKLY, listener);
	}
	public static void getWeeklyDetail(Context context,String id,ResponseListener listener){
		HTTPUtils.get(context,String.format(TKContants.Url.WEEKLY_DETAIL_FORMAT,id),listener);
	}
	
	public static void getSearchResult(Context context, int position, String keyword, ResponseListener listener)
	{
		HTTPUtils.get(context, TKContants.Url.SEARCH_CATEGORYS[position]+keyword, listener);
	}
	
	public static void getWebsite(Context context, String dataurl, ResponseListener listener)
	{
		HTTPUtils.get(context, dataurl, listener);
	}
	
	public static void getWebsiteDetail(Context context, boolean website, String id, ResponseListener listener)
	{
		if (website)
		{
			HTTPUtils.get(context, String.format(TKContants.Url.WEBSITE_DETAIL_FORMAT,id), listener);
		}
		else
		{
			HTTPUtils.get(context, String.format(TKContants.Url.TOPIC_DETAIL_FORMAT,id), listener);
		}
	}
	
	public static void getArticleDetail(Context context, String articleId, ResponseListener listener)
	{
		HTTPUtils.get(context, String.format(TKContants.Url.ARTICLE_DETAIL_FORMAT, articleId), listener);
	}
	
	public static void getUpdataMsg(Context context, ResponseListener listener)
	{
		HTTPUtils.get(context, TKContants.Url.UPDATE_MSG, listener);
	}
	public static void getUpdateLog(Context context,ResponseListener listener)
	{
		HTTPUtils.get(context,TKContants.Url.UPGRADE_LOG,listener);
	}
}
