package com.example.ljy.toolcool2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljy.fragment.ArticleFragment;
import com.example.ljy.fragment.MineFragment;
import com.example.ljy.fragment.SiteFragment;
import com.example.ljy.fragment.WeekFragment;
import com.example.ljy.utils.DayNightMode;
import com.example.ljy.utils.SPUtils;
import com.example.ljy.utils.TKContants;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    //导航栏控件
    private TextView textView;
    private ImageView imgNavHead;
    private Object fragmentContext;
    private ActionBar supportActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化日夜间模式，必须在setContentView前设置
        DayNightMode.initDayNightMode(MainActivity.this);
        setContentView(R.layout.activity_main);
        //注册butterknife
        ButterKnife.bind(this);
        initUI();
    }


    private void initUI() {
        /*******1.沉浸式状态栏*********/
        initSystembarTint();
        /*******2.初始化菜单（包括侧滑导航栏和toolbar）***************/
        initMenu();
    }

    private void initMenu() {

        /*******2.初始化菜单（包括侧滑导航栏和toolbar）***************/
        //设置toolbar
        setSupportActionBar(toolbarMain);
        supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("首页");
        //控制侧滑菜单
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);//设置导航栏点击事件
        navView.setItemIconTintList(null);//设置选中图片显示图片自身颜色
        //设置默认选中第一项：比较麻烦
        // 先将整个menu设置成不可选中，再设置第一行可选中
        //再设置选中第一行
        //最后在nav行点击事件中设置点击的行为可选中
        navView.getMenu().setGroupCheckable(R.id.nav_select,false,false);
        navView.getMenu().getItem(0).setCheckable(true);
        navView.setCheckedItem(R.id.nav_home);//初始化默认选中页面

        showFragMemt(new ArticleFragment());
        View headerView = navView.getHeaderView(0);
    }

    private void initSystembarTint() {
        /*******1.沉浸式状态栏*********/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        boolean nightMode = SPUtils.isNightMode(MainActivity.this);
        if (nightMode) {
            tintManager.setStatusBarTintResource(R.color.bg_toolbar_night);

        } else {
            tintManager.setStatusBarTintResource(R.color.bg_toolbar_day);

        }
    }


    /**
     * 设置返回键点击事件
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * 设置选项菜单点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_onlyChinese) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置导航栏菜单点击事件
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        /*******图片设置state_checked*****/
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        item.setCheckable(true);
        Log.e("点击了item",item.getItemId()+"");
        Bundle bundle = new Bundle();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            supportActionBar.setTitle("首页");
            fragment = new ArticleFragment();
            showFragMemt(fragment);
        } else if (id == R.id.nav_site) {
            supportActionBar.setTitle("站点");
            bundle.putString("url", TKContants.Url.WEBSITE);
            fragment = new SiteFragment();
            fragment.setArguments(bundle);
            showFragMemt(fragment);
        } else if (id == R.id.nav_topic) {
            supportActionBar.setTitle("主题");
            bundle.putString("url", TKContants.Url.TOPIC);
            fragment = new SiteFragment();
            fragment.setArguments(bundle);
            showFragMemt(fragment);

        } else if (id == R.id.nav_weekly) {
            supportActionBar.setTitle("周刊");
            bundle.putString("url", TKContants.Url.WEEKLY);
            fragment = new WeekFragment();
            fragment.setArguments(bundle);
            showFragMemt(fragment);
        } else if (id == R.id.nav_mine) {
            supportActionBar.setTitle("我的推酷");
            fragment = new MineFragment();
            showFragMemt(fragment);
        } else if (id == R.id.nav_dayNight_mode) {
            boolean isNight = SPUtils.isNightMode(MainActivity.this);
            if (isNight) {
                DayNightMode.setDayNightMode(MainActivity.this, false);
                Log.e(""+isNight,item.getTitle().toString());

            } else {
                DayNightMode.setDayNightMode(MainActivity.this, true);
                Log.e(""+isNight,item.getTitle().toString());
            }
        } else if (id == R.id.nav_offline_down) {

            Toast.makeText(MainActivity.this, "离线下载", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Toast.makeText(MainActivity.this, "相关设置", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showFragMemt(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_context, fragment).commit();
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
