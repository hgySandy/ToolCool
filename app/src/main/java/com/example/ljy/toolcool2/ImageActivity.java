package com.example.ljy.toolcool2;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.ljy.utils.DayNightMode;
import com.example.ljy.utils.SPUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.xinbo.utils.UILUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

@SuppressLint("NewApi")
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 日夜间模式初始化必须放在oncreat之前
        DayNightMode.initDayNightMode(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initUI();

        // //
        // final PhotoViewAttacher attacher = new PhotoViewAttacher(photoView);
        // //加载图片
        // Picasso.with(this)
        // .load(getIntent().getStringExtra("imageurl"))
        // .into(photoView, new Callback() {
        // @Override
        // public void onSuccess() {
        // attacher.update();
        // }
        //
        // @Override
        // public void onError() {
        // }
        // });
    }
    private void initUI() {
        /********************/
        /***** 沉浸式菜单栏 ***/
        /********************/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.bg_toolbar_night);

        // 查找控件id
        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);
        String imgurl = getIntent().getStringExtra("imageurl");
        UILUtils.displayImage(imgurl, photoView);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
