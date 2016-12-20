package com.example.ljy.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.ljy.model.ArticleContextBean.ArticlesBean;
import com.example.ljy.model.DetailBean;
import com.example.ljy.model.DetailBean.ArticleBean;
import com.example.ljy.model.DetailBean.ArticleBean.ImagesBean;
import com.example.ljy.model.DetailBean.ArticleBean.TopicsBean;
import com.example.ljy.model.DetailBean.SiteBean;
import com.example.ljy.toolcool2.ImageActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.SPUtils;
import com.example.ljy.utils.SQLUtils;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.ResponseListener;
import com.xinbo.utils.UILUtils;
import com.xinbo.widget.ListView4ScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "NewApi"})
public class ArticleDetailFragment extends Fragment {

    @BindColor(R.color.bg_mine_night)
    int bg_web_night;
    @BindColor(R.color.bg_mine_day)
    int bg_web_day;
    @BindView(R.id.tv_detail_title)
    TextView mtv_title;
    @BindView(R.id.tv_detail_subtitle)
    TextView mtv_subtitle;
    @BindView(R.id.webview_detail)
    WebView mwebview;
    @BindView(R.id.listview_detail_foot)
    ListView4ScrollView listViewFoot;

    private Unbinder unbinder;
    private View view;
    private DetailFootAdapter detailFootAdapter;
    private String articleId;
    private ArticlesBean collectarticle;
    private SiteBean site;
    private List<TopicsBean> topics = new ArrayList<>();
    private boolean nightMode;
    private String title;
    private String img;
    //    private String content;

    //    // public static final int TYPE = TKContants.Type.DETAIL_ARTICL_FRAGMENT;


//    private LayoutInflater inflater = null;


    // private SharedPreferences sp;

    public ArticleDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Log.e("ArticleDetailFragment", "onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_detail_article, container, false);
            unbinder = ButterKnife.bind(this, view);
            initUI();
            initData();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();

        }
        super.onDestroy();
    }

    private void initUI() {
        /*****************/
        /** 1.webview **/
        /*****************/
        mwebview.getSettings().setJavaScriptEnabled(true);// 启用javascript
        mwebview.addJavascriptInterface(new scriptInterface(getActivity()), "aaaa");// 向服务器提供一个接口
        mwebview.setWebViewClient(new MyWebViewClient());// load完成之后添加点击监听事件
        int level = SPUtils.getTextSize(getActivity());
        mwebview.getSettings().setTextZoom(30 * (level + 1));

        nightMode = SPUtils.isNightMode(getActivity());
        if (nightMode) {
            mwebview.setBackgroundColor(bg_web_night);
        } else
            mwebview.setBackgroundColor(bg_web_day);


        /*****************/
        /**** 2.listview **/
        /*****************/
        listViewFoot.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.item_detail_footlist_header, null));
        detailFootAdapter = new DetailFootAdapter();
        listViewFoot.setAdapter(detailFootAdapter);
    }

    private void initData() {
        //初始化对象
        initCollectarticle();
        // Log.e("articleId", articleId);
        ApiClient.getArticleDetail(getActivity(), articleId, new ResponseListener() {

            @Override
            public void onResponse(String jsondata) {
                DetailBean detail = GsonUtils.parseJSON(jsondata, DetailBean.class);// 解析
                /*****************/
                /**** 1.初始化head **/
                /*****************/
                title = detail.getArticle().getTitle();
                img = detail.getArticle().getImg();
                String feedTitle = detail.getArticle().getFeed_title();
                String time = detail.getArticle().getTime();
                mtv_title.setText(title);
                mtv_subtitle.setText(feedTitle + "      " + time);
                /*****************/
                /**** 2.初始化webview **/
                /*****************/
//                content = detail.getArticle().getContent();
//                List<ImagesBean> images = detail.getArticle().getImages();
//                String converContent = converConstant(content);// 设置图片大小
                String converContent = convertBody(detail);

                String converContent2 = initContent(converContent, nightMode, nightMode);
                // Log.e("converContent", converContent);
                mwebview.loadDataWithBaseURL(null, converContent2, "text/html", "utf-8", null);

                // TODO 设置加载完成监听来显示和隐藏head和foot的控件(有时间做一下)
                /*****************/
                /**** 3.初始化foot **/
                /*****************/
                site = detail.getSite();
                topics = detail.getArticle().getTopics();
                detailFootAdapter.notifyDataSetChanged();

            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

            }
        });

    }

    private void initCollectarticle() {
        ArticlesBean intentcollectarticle = (ArticlesBean) getActivity().getIntent().getSerializableExtra("collectarticle");

        if (intentcollectarticle == null) {
            Log.e("initCollectarticle", "empty");
            Intent intent = getActivity().getIntent();
            articleId = intent.getStringExtra("urlID");
            String collectTitle = intent.getStringExtra("collecttitle");
            collectarticle = new ArticlesBean();
            collectarticle.setTitle(collectTitle);
            collectarticle.setArticleUrl(articleId);
            collectarticle.setFeed_title("");
            collectarticle.setTime("");
        } else {
            collectarticle = intentcollectarticle;
            articleId = collectarticle.getArticleUrl();

        }
    }

//    // 图片大小自适应。
//    private String converConstant(String htmltext) {
//
//        Document doc = Jsoup.parse(htmltext);// 转换成document
//        Elements elements = doc.getElementsByTag("img");// 获取img标签内容
//        // elements javascript=doc.
//        for (Element element : elements) {
//            element.attr("width", "100%").attr("height", "auto");
//        }
//        return doc.toString();
//    }

    //     方法2 自己写 不用依赖库
    protected String convertBody(DetailBean detail) {
        /****修改图片大小***/
        ArticleBean article = detail.getArticle();
        String html = article.getContent();
        List<ImagesBean> images = article.getImages();
        // 不用获取高度
        String srcSize = " width=\"100%\" height=\"auto\"";
        for (int i = 0; i < images.size(); i++) {
            ImagesBean image = images.get(i);
            String src = image.getSrc();
            html = html.replace(src + "\"", src + "\"" + srcSize);
        }

        /***修改字体颜色*****/
        return html;
    }

    // 注入js函数监听
    @JavascriptInterface
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        mwebview.loadUrl("javascript:(function(){" + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{" + "    objs[i].onclick=function()  " + "    {  "
                + "        window.aaaa.openImage(this.src);  " + "    }  " + "}" + "})()");
    }

    // js通信接口
    class scriptInterface {

        private Context context;

        public scriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(String img) {
            // Log.e("img", img);
            // 跳转img页面
            Intent intent = new Intent(getActivity(), ImageActivity.class);
            intent.putExtra("imageurl", img);
            startActivity(intent);

        }
    }

    // 设置加载完成后图片监听
    private class MyWebViewClient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();
        }
    }

    /*****
     * 显示不同的toolbar
     *******/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Log.e("ArticleDetailFragment", "onCreate");
        // 设置toolbar显示当前fragment的菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // 清楚之前的menu
        menu.clear();
        // 设置fragment菜单
        inflater.inflate(R.menu.article_detail, menu);

        /***** 收藏 ********/
        MenuItem item = menu.findItem(R.id.item1);
//        initCollectarticle();
        boolean isCollect = SQLUtils.isCollect(getActivity(), collectarticle);
        if (isCollect) {
            item.setTitle("取消收藏");
        }
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("item", item.getItemId() + "");
        switch (item.getItemId()) {
            case R.id.item0:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.item1:
                boolean isCollect = SQLUtils.isCollect(getActivity(), collectarticle);
                if (isCollect) {
                    SQLUtils.cancleCollect(getActivity(), collectarticle);
                    item.setTitle("添加收藏");

                } else {
                    item.setTitle("取消收藏");
                    SQLUtils.collect(getActivity(), collectarticle);

                }
                break;
            case R.id.item2:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share_main:
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//                oks.setTitle("标题");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText(title);
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//                oks.setImageUrl(img);
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
//                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
//                oks.setSite("ShareSDK");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//                oks.setSiteUrl("http://sharesdk.cn");
                // 启动分享GUI
                oks.show(getActivity());

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DetailFootAdapter extends BaseAdapter {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Log.e("position", "" + position);
            View view = null;
            Holder holder = null;

            // 查找布局转化优化
            if (convertView != null) {
                view = convertView;
                holder = (Holder) view.getTag();
            } else {
                view = getActivity().getLayoutInflater().inflate(R.layout.item_detail_footlist, null);
                // 控件查找优化
                ImageView img_head = (ImageView) view.findViewById(R.id.img_item_detail_footlist_head);
                TextView tv_content = (TextView) view.findViewById(R.id.tv_item_detail_footlist_content);
                holder = new Holder(tv_content, img_head);
                view.setTag(holder);
            }
            // 判断site是否为空再进行显示
            String imgurl = null;
            String title = null;
            if (site == null) {
                imgurl = topics.get(position).getImage();
                title = topics.get(position).getName();

            } else {
                if (position == 0) {

                    imgurl = site.getImage();
                    title = site.getTitle();
                } else {
                    imgurl = topics.get(position - 1).getImage();
                    title = topics.get(position - 1).getName();

                }
            }
            holder.tv_content.setText(title);
            UILUtils.displayCircleImage(imgurl, holder.imghead);
            // 显示图片
            return view;
        }

        private class Holder {
            TextView tv_content;
            ImageView imghead;

            public Holder(TextView tv_content, ImageView imghead) {
                super();
                this.tv_content = tv_content;
                this.imghead = imghead;
            }

            public Holder() {
                super();
            }

        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            // 初始化和Notify都会调用，需要对site进行判断（site在initdata初始化）
            int i = 0;
            if (site != null) {
                i = 1;
            }

            return topics.size() + i;
        }
    }

    /**
     * @param content
     * @param night
     * @param flag
     * @return 修改字体颜色和webview背景
     */
    private String initContent(String content, boolean night, boolean flag) {
        try {
            InputStream inputStream = getResources().getAssets().open(
                    "discover.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream), 16 * 1024);
            StringBuilder sBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            String modelHtml = sBuilder.toString();
            inputStream.close();
            reader.close();

            String contentNew = modelHtml.replace(
                    "<--@#$%discoverContent@#$%-->", content);
            if (night) {
                contentNew = contentNew.replace("<--@#$%colorfontsize2@#$%-->",
                        "color:#8f8f8f ;");
            } else {
                contentNew = contentNew.replace("<--@#$%colorfontsize2@#$%-->",
                        "color:#333333 ;");
            }
            if (flag) {//设置背景
                contentNew = contentNew.replace(
                        "<--@#$%colorbackground@#$%-->", "background:#333333");
            } else {
                contentNew = contentNew.replace(
                        "<--@#$%colorbackground@#$%-->", "background:#eeeeee");
            }
            return contentNew;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
