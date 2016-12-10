package com.example.ljy.toolcool2;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.ljy.subFragment.AboutUsFragment;
import com.example.ljy.subFragment.LoginFragment;
import com.example.ljy.subFragment.SearchFragment;
import com.example.ljy.subFragment.SettingFragment;
import com.example.ljy.utils.DayNightMode;
import com.example.ljy.utils.SPUtils;
import com.example.ljy.utils.TKContants;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class CommonActivity extends AppCompatActivity {

    //
    private LayoutInflater inflater;
    private SharedPreferences sp;
    private ActionBar supportActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         日夜间模式初始化必须放在oncreat之前
        DayNightMode.initDayNightMode(this);
//         Log.e("CommonActivity", "onCreate");
        setContentView(R.layout.activity_common);
        initUI();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
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

    private void initUI() {
        /********************/
        /***** 沉浸式菜单栏 ***/
        /********************/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //设置状态栏颜色
        boolean nightMode = SPUtils.isNightMode(CommonActivity.this);
        if (nightMode) {
            tintManager.setStatusBarTintResource(R.color.bg_toolbar_night);
        } else {
            tintManager.setStatusBarTintResource(R.color.bg_toolbar_day);
        }
        sp = getSharedPreferences(TKContants.Name.SP_FILE_NAME, MODE_PRIVATE);
        inflater = getLayoutInflater();
        initToolbar();
        initFragment();

    }
    private void initFragment() {
        int type = getIntent().getIntExtra("type", 0);
        // Log.e("commonActivity", "type:"+type);
        Fragment fragment = null;
        switch (type) {
            case TKContants.Type.DETAIL_ARTICL_FRAGMENT:
//                fragment = new ArticleDetailFragment();
//                // 新手引导
//                boolean isFirstToDetail = SPUtils.isFirstToDetail(CommonActivity.this);
//                if (isFirstToDetail) {
//                    new ShowcaseView.Builder(this).setTarget(new ViewTarget(R.id.view_caseview, this))
//                            .setStyle(com.github.amlcurran.showcaseview.R.style.ShowcaseButton)
//                            .setContentTitle("点击更多可以添加字体，添加待读等.").build();
//                    supportActionBar.setTitle("详情");
//                    SPUtils.setNoFistToDetail(CommonActivity.this);
//
//                }

                break;
            case TKContants.Type.SEARCH:
//                fragment=new BlankFragment();
                fragment = new SearchFragment();
                supportActionBar.setTitle("common");
                break;
            case TKContants.Type.LOGIN:
                fragment = new LoginFragment();
                supportActionBar.setTitle("登录");
                break;
            case TKContants.Type.COLLECT:
//                fragment = new CollectFragment();
//                supportActionBar.setTitle("我的收藏");
                break;
            case TKContants.Type.ABOUT_SETTING:
                fragment = new SettingFragment();
                supportActionBar.setTitle("相关设置");
                break;
            case TKContants.Type.ABOUT_US:
                fragment = new AboutUsFragment();
                supportActionBar.setTitle("关于我们");
                break;

            default:
                break;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.common_fragment_context, fragment).commit();
    }

    private void initToolbar() {

        /*****************/
        /**** 1.toolbar ****/
        /*****************/
        // 使用5.0toolbar风格要在getsupportActionBar前设置，直接覆盖actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();// 获取actionbar
        supportActionBar.setDisplayHomeAsUpEnabled(true);// 设置返回键
        supportActionBar.setDisplayShowTitleEnabled(true);// 允许显示标题
        supportActionBar.setTitle("标题");

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
//
//    class MyShowcaseDrawer implements ShowcaseDrawer {
//
//        @Override
//        public void setShowcaseColour(int color) {
//
//        }
//
//        @Override
//        public void drawShowcase(Bitmap buffer, float x, float y, float scaleMultiplier) {
//
//        }
//
//        @Override
//        public int getShowcaseWidth() {
//            return 0;
//        }
//
//        @Override
//        public int getShowcaseHeight() {
//            return 0;
//        }
//
//        @Override
//        public float getBlockedRadius() {
//            return 0;
//        }
//
//        @Override
//        public void setBackgroundColour(int backgroundColor) {
//
//        }
//
//        @Override
//        public void erase(Bitmap bitmapBuffer) {
//
//        }
//
//        @Override
//        public void drawToCanvas(Canvas canvas, Bitmap bitmapBuffer) {
//
//        }
//
//    }
}
