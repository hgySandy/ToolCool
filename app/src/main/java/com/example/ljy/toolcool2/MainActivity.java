package com.example.ljy.toolcool2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
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
import com.xinbo.utils.UILUtils;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

//TODO 切换fragment的时候会重复创建fragment（可能会产生卡顿），虽然GC会自动回收垃圾对象，但是有时间可以做优化
//优化思路：不创建新的fragment，保存之前的fragment，用hide add show取代replace来处理fragment.(replace效率低，每次都实例化一个对象，官方建议在上一个fragment不使用时才用这个方法)
//此优化的缺点：内存占用率高
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindColor(R.color.bg_toolbar_night)
    int bgnavgation;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    //导航栏控件
    private ActionBar supportActionBar;
    private View headerView;
    private Platform weibo;
    private boolean sureexit = false;
    private int clickId;
    private int showFragmentId=R.id.nav_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Mainactivity", "onCreate");
        //初始化日夜间模式，必须在setContentView前设置
        DayNightMode.initDayNightMode(MainActivity.this);
        setContentView(R.layout.activity_main);
        //注册butterknife
        ButterKnife.bind(this);
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Mainactivity", "onResume");

        setSelectItem();
        //设置显示页面
        showFragMemt(showFragmentId);
        //设置导航栏头像和名称
        initNavHead();

    }

    private void setSelectItem() {
        //设置默认选中第一项：比较麻烦
        // 先将整个menu设置成不可选中，再设置第一行可选中
        //再设置选中第一行
        //最后在nav行点击事件中设置点击的行为可选中
        navView.getMenu().setGroupCheckable(R.id.nav_select, false, false);
        navView.getMenu().getItem(0).setCheckable(true);
        navView.setCheckedItem(showFragmentId);//初始化默认选中页面
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
                this, drawerLayout, toolbarMain, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);//设置导航栏点击事件
        navView.setItemIconTintList(null);//设置选中图片显示图片自身颜色
        headerView = navView.getHeaderView(0);

    }

    /**
     * 初始化导航栏头像，日夜间模式
     */
    private void initNavHead() {
        //navigation头像日夜间模式显示背景
        boolean nightMode = SPUtils.isNightMode(this);
        if (nightMode) {
            headerView.setBackgroundColor(bgnavgation);
        }
        weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
        String token = weibo.getDb().getToken();

        //设置头部点击事件 判断是否登录 进行跳转
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommonActivity.class);
                if (token.equals("")) {
                    intent.putExtra("type", TKContants.Type.LOGIN);
                } else {
                    intent.putExtra("type", TKContants.Type.ACCOUNT_INFO);
                }
                startActivity(intent);
            }
        });
        ImageView headimg = (ImageView) headerView.findViewById(R.id.img_nav_head);
        TextView tvName = (TextView) headerView.findViewById(R.id.tv_nav_head_title);
        String userIcon = weibo.getDb().getUserIcon();
        String username = weibo.getDb().getUserName();

        //根据是否有token设置显示头像
        if (!token.equals("")) {
            UILUtils.displayImage(userIcon, headimg);
            tvName.setText(username);
        }
        else{
            headimg.setImageResource(R.mipmap.nav_user_default);
            tvName.setText("注册/登录");
        }
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
        clickId = item.getItemId();

        switch (clickId) {
            default:
                //设置可以checkable选项，改变颜色
                item.setCheckable(true);
                //显示fragment
                showFragMemt(clickId);
                break;
            case R.id.nav_dayNight_mode://切换日夜间模式
                boolean isNight = SPUtils.isNightMode(MainActivity.this);
                if (isNight) {
                    DayNightMode.setDayNightMode(MainActivity.this, false);

                } else {
                    DayNightMode.setDayNightMode(MainActivity.this, true);
                }
                break;
            case R.id.nav_offline_down:
                Toast.makeText(MainActivity.this, "离线下载", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting://跳转设置页面
                Intent intent = new Intent(this, CommonActivity.class);
                intent.putExtra("type", TKContants.Type.ABOUT_SETTING);
                startActivity(intent);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void showFragMemt(int itemId) {
        showFragmentId =itemId;
        Bundle bundle = new Bundle();
        Fragment fragment = null;
        switch (itemId){
            case R.id.nav_home:
                supportActionBar.setTitle("首页");
                fragment = new ArticleFragment();
                break;
            case R.id.nav_site:
                supportActionBar.setTitle("站点");
                bundle.putString("url", TKContants.Url.WEBSITE);
                fragment = new SiteFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_topic:
                supportActionBar.setTitle("主题");
                bundle.putString("url", TKContants.Url.TOPIC);
                fragment = new SiteFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_weekly:
                supportActionBar.setTitle("周刊");
                bundle.putString("url", TKContants.Url.WEEKLY);
                fragment = new WeekFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.nav_mine:
                supportActionBar.setTitle("我的推酷");
                fragment = new MineFragment();
                break;
        }
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


    /**
     * 确认退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!sureexit) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            sureexit = true;
            new Handler().postDelayed(() -> sureexit = false, 2000);
        } else {
            finish();
        }

        return false;
    }
}
