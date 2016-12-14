package com.example.ljy.utils;

import java.util.List;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.ljy.model.ArticleContextBean.ArticlesBean;

import android.app.Activity;

public class SQLUtils {

	public static boolean isCollect(Activity activity, ArticlesBean collectarticle) {
		collectarticle = (ArticlesBean) activity.getIntent().getSerializableExtra("collectarticle");
		ArticlesBean queryArtcile = new Select().from(ArticlesBean.class)
				.where("articleurl=?", collectarticle.getArticleUrl()).executeSingle();
		boolean iscollect = false;
		if (queryArtcile != null) {
			iscollect = true;
		}
		return iscollect;
	}

	public static void cancleCollect(Activity activity,ArticlesBean collectarticle) {
		collectarticle = (ArticlesBean) activity.getIntent().getSerializableExtra("collectarticle");
		new Delete().from(ArticlesBean.class).where("articleurl=?", collectarticle.getArticleUrl())
				.executeSingle();
	}
	public static void collect(Activity activity,ArticlesBean collectarticle){
		collectarticle = (ArticlesBean) activity.getIntent().getSerializableExtra("collectarticle");
		ArticlesBean collectarticle2 = collectarticle.clone();
		collectarticle2.save();
	}
	
	// 查询整张表
	public static List<ArticlesBean> queryCollected() {
		List<ArticlesBean> list = new Select().from(ArticlesBean.class).execute();
		return list;
	}

}
