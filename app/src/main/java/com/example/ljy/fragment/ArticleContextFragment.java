package com.example.ljy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.andexert.library.RippleView;
import com.android.volley.VolleyError;
import com.example.ljy.model.ArticleContextBean;
import com.example.ljy.model.ArticleContextBean.ArticlesBean;
import com.example.ljy.model.SearchSiteBean.TopicsBean;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.ImageLoadUtils;
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
    private SiteDataAdapter siteDataAdapter;
    private List<ArticlesBean> articles = new ArrayList<>();
    private List<TopicsBean> sites = new ArrayList<>();
    //    private DataAdapter dataAdapter;
//    private List<Article> articles = new ArrayList<Article>();
//    private List<Item> searchsiteData = new ArrayList<>();
//    private List<Topic> searchTopicData = new ArrayList<>();

    //    private int tabposition;
//    private LinearLayout linearLayout;
//    private Button button;
//    private String searchcontext;
//    private String collect;
//    private ListView listView;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.e("ArticleContextFragment","onCreateView");
        //获取参数
        initBundleArg();
        //初始化UI
        if (view == null) {
//            Log.e("ArticleContextFragment", "第一次onCreateView");
            view = inflater.inflate(R.layout.fragment_article_context, container, false);
            unbinder = ButterKnife.bind(this, view);
            initUI();
//        初始化数据
            if (searchcontext != null) {//搜索界面初始化数据
                initSearchData();
            } else {
                initData();
            }
        }
        return view;
    }


    private void initUI() {
        if (searchcontext!=null&&tabposition!=0){
            siteDataAdapter = new SiteDataAdapter(getActivity(), R.layout.item_search_result, sites);
            listviewArticleContext.setAdapter(siteDataAdapter);
        }else {
            articleDataAdapter = new ArticleDataAdapter(getActivity(), R.layout.item_list_article_context, articles);
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
//                    SearchSiteDate searchData = GsonUtils.parseJSON(s, SearchSiteDate.class);
//                    searchsiteData = searchData.getItems();
//                    Log.e("onResponse", "" + tabposition);
//                    dataAdapter.notifyDataSetChanged();
                } else if (tabposition == 1) {
//                    SearchSiteBean parseJSON = GsonUtils.parseJSON(s, SearchSiteBean.class);
//                    searchTopicData = parseJSON.getTopics();
//
//                    dataAdapter.notifyDataSetChanged();
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
//        if (collect != null) {
//            articles = SQLUtils.queryCollected();
//            dataAdapter.notifyDataSetChanged();
//            return;
//
//        }
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
//                    Log.e("article",articles.toString());
                    articleDataAdapter.notifyDataSetChanged();
                }

                @Override
                public void onErrorResponse(VolleyError arg0) {

                }
            });
        }
    }

    @OnClick(R.id.btn_tuijian_login)
    public void onClick() {
        //TODO 登录
        Log.e("button", "点击了登录按钮");
        Intent intent = new Intent(getActivity(), CommonActivity.class);
        intent.putExtra("type", TKContants.Type.LOGIN);
        startActivity(intent);
    }


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
            RippleView rippleView = viewHolder.getView(R.id.ripple_article_item_list_context);
            rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    //todo 跳转详情页面
//                    Intent intent = new Intent(getActivity(), CommonActivity.class);
//                    intent.putExtra("collectarticle", articles.get(position));
//                    intent.putExtra("type", TKContants.Type.DETAIL_ARTICL_FRAGMENT);
//                    startActivity(intent);
//                    Log.e("convert", "sdfsd");
                }
            });
        }
    }

    private class SiteDataAdapter extends CommonAdapter<TopicsBean> {

        public SiteDataAdapter(Context context, int layoutId, List<TopicsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, TopicsBean item, int position) {
            viewHolder.setText(R.id.tv_search_result_site_name, item.getName());
            ImageView img = viewHolder.getView(R.id.img_search_result_site_head);
            UILUtils.displayImage(item.getImage(), img);
        }
    }


//    private class DataAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            if (searchcontext != null && tabposition != 0) {
//                if (tabposition == 1) {
//                    return searchTopicData.size();
//
//                } else {
//
//                    return searchsiteData.size();
//                }
//            }
//
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
//        public View getView(final int position, View convertView, ViewGroup parent) {
//
//            View view = null;
//            Holder holder = null;
//            // 搜索
//            if (searchcontext != null && tabposition != 0) {
//                // 查找布局转化优化
//                if (convertView != null) {
//                    view = convertView;
//                    holder = (Holder) view.getTag();
//                } else {
//                    view = getActivity().getLayoutInflater().inflate(R.layout.item_search_result, null);
//                    TextView title = (TextView) view.findViewById(R.id.tv_search_result_site_name);
//                    ImageView img_head = (ImageView) view.findViewById(R.id.img_search_result_site_head);
//                    holder = new Holder(title, img_head);
//                    view.setTag(holder);
//
//                }
//                String title = null;
//                String imgurl = null;
//                if (tabposition == 1) {
//                    title = searchTopicData.get(position).getName();
//                    imgurl = searchTopicData.get(position).getImage();
//
//                } else {
//
//                    title = searchsiteData.get(position).getName();
//                    imgurl = searchsiteData.get(position).getImage();
//                }
//
//                holder.title.setText(title);
//                ImageLoadUtils.displayImage(getActivity(), imgurl, holder.img);
//                return view;
//            }
//
//
//
//        }
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.e("ArticleContextFragment","onDestroyView"+tabposition);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
//        Log.e("ArticleContextFragment","onDestroy"+tabposition);
    }
}
