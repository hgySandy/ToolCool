package com.example.ljy.toolcool2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.example.ljy.model.StartPageBean;
import com.example.ljy.model.StartPageBean.ItemsBean;
import com.example.ljy.utils.TKContants;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.ResponseListener;
import com.xinbo.utils.UILUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sp;
    @BindView(R.id.img_splash_background)
    ImageView imgSplashBackground;
    @BindView(R.id.img_splash_src)
    ImageView imgSplashSrc;
    @BindView(R.id.activity_splash)
    RelativeLayout activitySplash;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        initUI();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }

    private void initUI() {
        //直接设置显示透明
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置开启显示图片
        setStartImage();
        // 下载图片
        downStartImages();
        // 打开MainActivity
        startMainActivity();

    }

    private void startMainActivity() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgSplashBackground, "alpha", 0f, 1.0f);
        animator.setDuration(2000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setStartImage() {
        // 打开SP 没有的话创建
        sp = getSharedPreferences(TKContants.Name.SP_FILE_NAME, MODE_PRIVATE);
        // 获取要显示已缓存的图片地址
        String startImgUrl = sp.getString("startimgurl", TKContants.Name.SP_START_DEFAULT);
        if (!startImgUrl.equals(TKContants.Name.SP_START_DEFAULT)) {
            Log.e("startImgUrl", startImgUrl);

            // 更换图片需要服务器那边修改startImgUrl
            // 图片已经缓存了所以没有开启其他线程加载图片，直接从SD卡中（开启SD权限）/memory cache（没开SD权限）中读取
            UILUtils.displayImage(startImgUrl, imgSplashBackground);
        }
    }

    public void downStartImages() {
        HTTPUtils.get(SplashActivity.this, TKContants.Url.START_PAGES, new ResponseListener() {

            @Override
            public void onResponse(String arg0) {
                StartPageBean imgdata = GsonUtils.parseJSON(arg0, StartPageBean.class);
                List<ItemsBean> imgs = imgdata.getItems();
                // 下载图片
                for (int i = 0; i < imgs.size(); i++) {
                    final String imgurl = imgs.get(i).getId();
                    // new imageView 否则只会下最后一张
                    UILUtils.displayImage(imgurl, new ImageView(SplashActivity.this));
                }
                // 获得下次要显示图片的地址
                String spImgurl = getSpUrl(imgs);
                // 将下次要显示的图片下载地址（已缓存）保存在sp中
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("startimgurl", spImgurl);
                edit.commit();
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("splashactivity", "下载图片异常");
            }

            /**
             * @param imgs
             * @return 获得下次要显示图片的地址
             */
            private String getSpUrl(List<ItemsBean> imgs) {
                DiskCache diskCache = ImageLoader.getInstance().getDiskCache();// 获取缓存路径
                File file = null;
                ArrayList<String> imgurls = new ArrayList<>();
                for (int i = 0; i < 9; i++) {
                    // int position = new Random().nextInt(imgs.size());
                    String spImgurl = imgs.get(i).getId();
                    file = diskCache.get(spImgurl);
                    if (file != null) {
                        imgurls.add(spImgurl);
                    }
                }
                Log.e("url", "" + imgurls.size());
                if (imgurls.size() == 0)
                    return TKContants.Name.SP_START_DEFAULT;

                else {
                    return imgurls.get(new Random().nextInt(imgurls.size()));
                }
            }

        });
    }
}