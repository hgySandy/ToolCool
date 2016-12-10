package com.example.ljy.utils;

import com.example.ljy.toolcool2.R;

import java.util.ArrayList;
import java.util.HashMap;


public final class TKContants {
	public static final class Url {
		private static final String HOST = "http://api.tuicool.com";
		private static final String ARTICLE_URL_FORMAT = HOST
				+ "/api/articles/hot.json?size=30&lang=-1&cid=%s&is_pad=1";

		public static final String HOT = String.format(ARTICLE_URL_FORMAT, "0");
		public static final String RECOMMEND = "";
		public static final String TECHNOLOGY = String.format(ARTICLE_URL_FORMAT, "101000000");
		public static final String ENTREPRENEURSHIP = String.format(ARTICLE_URL_FORMAT, "101040000");
		public static final String DIGITAL = String.format(ARTICLE_URL_FORMAT, "101050000");
		public static final String TECHNIQUE = String.format(ARTICLE_URL_FORMAT, "20");
		public static final String DESIGN = String.format(ARTICLE_URL_FORMAT, "108000000");
		public static final String MARKETING = String.format(ARTICLE_URL_FORMAT, "114000000");

		public static final String[] CATEGORYS = { HOT, RECOMMEND, TECHNOLOGY, ENTREPRENEURSHIP, DIGITAL, TECHNIQUE,
				DESIGN, MARKETING };
		public static final String SEARCH_ARTICLE = HOST + "/api/articles/search.json?lang=1&pn=0&kw=";
		public static final String SEARCH_TOPIC = HOST + "/api/topics/search.json?kw=";
		public static final String SEARCH_WEBSITE = HOST + "/api/sites/search.json?kw=";
		public static final String[] SEARCH_CATEGORYS = { SEARCH_ARTICLE, SEARCH_TOPIC, SEARCH_WEBSITE };
		public static final String WEEKLY = HOST + "/api/mag/home.json";

		public static final String ARTICLE_DETAIL_FORMAT = HOST + "/api/articles/%s.json?need_image_meta=1&type=1";

		public static final String START_PAGES = HOST + "/api/home/start_pages.json?type=0";
		public static final String UPGRADE_LOG = HOST + "/api/home/upgrade_logs.json";
		public static final String WEBSITE = HOST + "/api/sites/my_with_dirs.json";
		public static final String WEBSITE_DETAIL_FORMAT = HOST + "/api/sites/%s.json?pn=0&size=30&is_pad=1";
		public static final String TOPIC = HOST + "/api/topics/my_with_dirs.json";
		public static final String TOPIC_DETAIL_FORMAT = HOST
				+ "/api/topics/%s.json?pn=0&lang=-1&st=0&size=30&is_pad=1";
		/*
		 * private static final String HOST =
		 * "HOST://192.168.56.1:8080/tuiku_server/"; public static final String
		 * HOT = HOST + "tuiku_hot.txt"; public static final String RECOMMEND =
		 * ""; public static final String TECHNOLOGY = HOST + "keji.txt"; public
		 * static final String ENTREPRENEURSHIP = HOST + "tuiku_chuangye.txt";
		 * public static final String DIGITAL = HOST + "shuma.txt"; public
		 * static final String TECHNIQUE = HOST + "jishu.txt"; public static
		 * final String DESIGN = HOST + "sheji.txt"; public static final String
		 * MARKETING = HOST + "yingxiao.txt";
		 * 
		 * public static final String[] CATEGORYS = { HOT, RECOMMEND,
		 * TECHNOLOGY, ENTREPRENEURSHIP, DIGITAL, TECHNIQUE, DESIGN, MARKETING
		 * };
		 * 
		 * public static final String SEARCH_ARTICLE = HOST +
		 * "seach_android_article.txt"; public static final String SEARCH_TOPIC
		 * = HOST + "seach_android_topic.txt"; public static final String
		 * SEARCH_WEBSITE = HOST + "seach_android_site.txt"; public static final
		 * String[] SEARCH_CATEGORYS = { SEARCH_ARTICLE, SEARCH_TOPIC,
		 * SEARCH_WEBSITE};
		 * 
		 * public static final String WEEKLY = HOST + "zhoukan.txt";
		 * 
		 * public static final String HOT_DETAIL = HOST + "hot_detail.txt";
		 * public static final String UPGRADE_LOG = HOST + "upgradelog.txt";
		 * 
		 * public static final String WEBSITE = HOST + "zhandian.txt"; public
		 * static final String TOPIC = HOST + "zhuti.txt";
		 */

	}

	public static final class Name {
		public static final String SP_FILE_NAME = "appConfig";
		public static final String SP_START_DEFAULT="default";
		// TODO
		public static final String[] MINE_LIST1 = { "我的待读", "我的收藏", "我的推刊", "我的通知" };
		public static final String[] MINE_LIST2 = { "离线阅读", "意见反馈", "请求鼓励", "更多设置" };
	}

	public static final class DRAWABLE {
		public static final int[] MINE_SETTING = { R.drawable.my_kan, R.drawable.my_fav, R.drawable.my_tuikan,
				R.drawable.my_notification };
//		public static final int[] MINE_LIST2 = { R.drawable.offline, R.drawable.advices, R.drawable.ratting,
//				R.drawable.my_about };
	}
	public static final class WBCONANTS{
		public static final String APP_KEY      = "2101123000";		   // 应用的APP_KEY
		public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
		public static final String SCOPE = 							   // 应用申请的高级权限
				"email,direct_messages_read,direct_messages_write,"
						+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
						+ "follow_app_official_microblog,";
	}

	// public static final class Type
	// {
	// public static final int MY = 1;
	// public static final int COLLECT = 2;
	// public static final int DETAIL = 3;
	// public static final int SETTING = 4;
	// public static final int SETTING_MORE = 5;
	// public static final int UPGRADE_LOG = 6;
	// public static final int ABOUT = 7;
	// public static final int SEARCH_RESULT = 8;
	// public static final int WEEKLY = 9;
	// public static final int WEBSITE_DETAIL = 10;
	// public static final int USER_LOGIN = 11;
	// public static final int USER_REG = 12;
	// public static final int OFFLINE_READ = 13;
	// public static final int SHARE_SETTING = 14;
	// public static final int FEEKBACK = 15;
	// }

	public static final class Type {
		public static final int DETAIL_ARTICL_FRAGMENT = 0;
		public static final int SEARCH = 1;
		public static final int LOGIN = 2;
		public static final int COLLECT=3;
		public static final int ABOUT_SETTING=4;
		public static final int ABOUT_US =5 ;
	}

	public static final class Key {
		public static final String EXTRA_ARTICLE_KEY = "article";
		public static final String EXTRA_FRAGMENT_KEY = "type";
		public static final String EXTRA_TITLE_KEY = "title";
		public static final String EXTRA_SEARCH_KEY = "keyword";
		public static final String EXTRA_DETAILID_KEY = "detailId";
		public static final String EXTRA_SHOW_WEBSITE_KEY = "showWebsite";

		public static final String EXTRA_POSITION_KEY = "position";
		public static final String SP_DAYNIGHT_KEY = "isNight";
		public static final String SP_ONLYWIFI_KEY = "isOnlyWifi";
		public static final String SP_FONTSIZE_KEY = "fontSize";
		public static final String SP_FIRSTRUN_KEY = "isFirst";
	}
}
