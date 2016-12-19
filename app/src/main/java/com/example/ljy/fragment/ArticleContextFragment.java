package com.example.ljy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.android.volley.VolleyError;
import com.example.ljy.model.ArticleContextBean;
import com.example.ljy.model.ArticleContextBean.ArticlesBean;
import com.example.ljy.model.SearchSiteBean;
import com.example.ljy.model.SearchSiteBean.ItemsBean;
import com.example.ljy.model.SearchtopicBean;
import com.example.ljy.model.SearchtopicBean.TopicsBean;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.ImageLoadUtils;
import com.example.ljy.utils.SQLUtils;
import com.example.ljy.utils.TKContants;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.ResponseListener;
import com.xinbo.utils.UILUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleContextFragment extends Fragment {

    @BindView(R.id.btn_tuijian_login)
    Button btnTuijianLogin;
    @BindView(R.id.linearlayout_tuijian)
    LinearLayout linearlayoutTuijian;
    @BindView(R.id.listview_article_context)
    ListView listviewArticleContext;


    private Unbinder unbinder;
    private int tabposition;
    private String collect;
    private String searchcontext;


    private ArticleDataAdapter articleDataAdapter;
    private TopicsAdapter topicsAdapter;
    private SitesAdapter sitesAdapter;
    private List<ArticlesBean> articles = new ArrayList<>();
    private List<TopicsBean> searchTopics = new ArrayList<>();
    private List<ItemsBean> searchSites = new ArrayList<>();
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initBundleArg();
//        Log.e("articleContextFragment","onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_article_context, container, false);
            unbinder = ButterKnife.bind(this, view);
            initUI();
            if (searchcontext != null) {//搜索界面初始化数据
                initSearchData();
            } else {
                initData();
            }
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (collect != null)
            initCollect();

    }

    private void initUI() {
        if (searchcontext != null && tabposition != 0) {
            if (tabposition == 1) {//主题
                topicsAdapter = new TopicsAdapter(getActivity(), R.layout.item_search_result, searchTopics);
                listviewArticleContext.setAdapter(topicsAdapter);
            } else {//站点
                sitesAdapter = new SitesAdapter(getActivity(), R.layout.item_search_result, searchSites);
                listviewArticleContext.setAdapter(sitesAdapter);
            }
        } else {//文章
            articleDataAdapter = new ArticleDataAdapter(getActivity(), R.layout.item_list_article_context, articles);
//            articleDataAdapter=new ArticleDataAdapter();
            listviewArticleContext.setAdapter(articleDataAdapter);
        }

    }

    private void initBundleArg() {
        collect = getArguments().getString("collect");
        tabposition = getArguments().getInt("position");
        searchcontext = getArguments().getString("search");
    }

    private void initSearchData() {
        ApiClient.getSearchResult(getActivity(), tabposition, searchcontext, new ResponseListener() {
            @Override
            public void onResponse(String s) {
                if (tabposition == 2) {
                    SearchSiteBean searchData = GsonUtils.parseJSON(s, SearchSiteBean.class);
                    searchSites.clear();
                    searchSites.addAll(searchData.getItems());
                    sitesAdapter.notifyDataSetChanged();
                } else if (tabposition == 1) {
                    SearchtopicBean parseJSON = GsonUtils.parseJSON(s, SearchtopicBean.class);
                    searchTopics.clear();
                    searchTopics.addAll(parseJSON.getTopics());
                    topicsAdapter.notifyDataSetChanged();
                } else {
                    ArticleContextBean articleTuijian = GsonUtils.parseJSON(s, ArticleContextBean.class);
                    articles.clear();
                    articles.addAll(articleTuijian.getArticles());
//                    Log.e("article",articles.toString());
                    articleDataAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }


        });

    }


    private void initData() {
        if (collect != null) {
            initCollect();
            return;

        }
        /**判断是否登录再确定要不要加载Hot数据****/
        Platform weibo = ShareSDK.getPlatform(getContext(), SinaWeibo.NAME);
        String token = weibo.getDb().getToken();
        if (token.equals("") && tabposition == 1) {
            linearlayoutTuijian.setVisibility(View.VISIBLE);

        } else {
            ApiClient.get(getActivity(), tabposition, new ResponseListener() {

                @Override
                public void onResponse(String arg0) {
                    ArticleContextBean articleTuijian = GsonUtils.parseJSON(arg0, ArticleContextBean.class);
                    articles.clear();
                    articles.addAll(articleTuijian.getArticles());
                    Log.e("article", articles.size()+"");
                    articleDataAdapter.notifyDataSetChanged();
                }

                @Override
                public void onErrorResponse(VolleyError arg0) {

                }
            });
        }
    }

    private void initCollect() {
        articles.clear();
        articles.addAll(SQLUtils.queryCollected());
        //用下面这句取代上面两句会有问题
        //articles = SQLUtils.queryCollected();
        articleDataAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_tuijian_login)
    public void onClick() {
        //TODO 登录
        Log.e("button", "点击了登录按钮");
        Intent intent = new Intent(getActivity(), CommonActivity.class);
        intent.putExtra("type", TKContants.Type.LOGIN);
        startActivity(intent);
    }

//    private class ArticleDataAdapter extends BaseAdapter{
//
//        @Override
//        public int getCount() {
//            return articles.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view;
//            view= getActivity().getLayoutInflater().inflate(R.layout.item_list_article_context, null);
//            TextView tvTitle = (TextView) view.findViewById(R.id.tv_article_item_title);
//            TextView subTitle = (TextView) view.findViewById(R.id.tv_article_item_subtitle);
//            ImageView img = (ImageView) view.findViewById(R.id.img_article_item);
//            tvTitle.setText(articles.get(position).getTitle());
//            subTitle.setText(articles.get(position).getFeed_title());
//            ImageLoadUtils.displayImage(getActivity(),articles.get(position).getImg(),img);
//            Log.e("ArticleDataAdapter","convert"+position);
//            Log.e("ArticleDataAdapter",articles.get(position).getTitle());
//            return view;
//        }
//    }

    private class ArticleDataAdapter extends CommonAdapter<ArticlesBean> {


        public ArticleDataAdapter(Context context, int layoutId, List<ArticlesBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, ArticlesBean item, int position) {

            viewHolder.setText(R.id.tv_article_item_title, item.getTitle());
            viewHolder.setText(R.id.tv_article_item_subtitle, item.getFeed_title() + "  " + item.getTime());
            ImageView img_item = viewHolder.getView(R.id.img_article_item);
            ImageLoadUtils.displayImage(getActivity(), item.getImg(), img_item);
            Log.e("ArticleDataAdapter",viewHolder.toString());
            Log.e("ArticleDataAdapter","convert"+position);
            Log.e("ArticleDataAdapter",item.getTitle());
            RippleView rippleView = viewHolder.getView(R.id.ripple_article_item_list_context);
            rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    //todo 跳转详情页面
                    Intent intent = new Intent(getActivity(), CommonActivity.class);
                    intent.putExtra("collectarticle", articles.get(position));
                    intent.putExtra("type", TKContants.Type.DETAIL_ARTICL_FRAGMENT);
                    startActivity(intent);
                }
            });
        }
    }

    private class TopicsAdapter extends CommonAdapter<TopicsBean> {

        public TopicsAdapter(Context context, int layoutId, List<TopicsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, TopicsBean item, int position) {
            viewHolder.setText(R.id.tv_search_result_site_name, item.getName());
            ImageView img = viewHolder.getView(R.id.img_search_result_site_head);
            UILUtils.displayCircleImage(item.getImage(), img);
        }
    }

    private class SitesAdapter extends CommonAdapter<ItemsBean> {

        public SitesAdapter(Context context, int layoutId, List<ItemsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, ItemsBean item, int position) {
            viewHolder.setText(R.id.tv_search_result_site_name, item.getName());
            ImageView img = viewHolder.getView(R.id.img_search_result_site_head);
            UILUtils.displayCircleImage(item.getImage(), img);
        }
    }


    @Override
    public void onDestroyView() {
//        articles.clear();
        super.onDestroyView();
//        Log.e("ArticleContextFragment","onDestroyView"+tabposition);

    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();

        }
        super.onDestroy();
//        Log.e("ArticleContextFragment","onDestroy"+tabposition);
    }
}
