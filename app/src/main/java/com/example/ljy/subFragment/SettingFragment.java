package com.example.ljy.subFragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.andexert.library.RippleView.OnRippleCompleteListener;
import com.android.volley.VolleyError;
import com.example.ljy.model.UpdateBean;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.TKContants;
import com.example.ljy.utils.UpdateUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.AppRate;
import com.xinbo.utils.FileUtils;
import com.xinbo.utils.ResponseListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements OnRippleCompleteListener {

    @BindView(R.id.rv_setting_clearcache)
    RippleView llSettingClearcache;
    @BindView(R.id.rv_setting_share)
    RippleView llSettingShare;
    @BindView(R.id.rv_setting_more)
    RippleView llSettingMore;
    @BindView(R.id.rv_setting_yijianfankui)
    RippleView llSettingYijianfankui;
    @BindView(R.id.rv_setting_gengxinrizhi)
    RippleView llSettingGengxinrizhi;
    @BindView(R.id.rv_setting_jianchagengxin)
    RippleView llSettingJianchagengxin;
    @BindView(R.id.rv_setting_qiurentong)
    RippleView llSettingQiurentong;
    @BindView(R.id.rv_setting_fenxianghaoyou)
    RippleView llSettingFenxianghaoyou;
    @BindView(R.id.rv_setting_guanyuwomen)
    RippleView llSettingGuanyuwomen;
    @BindView(R.id.tv_setting_cacheNum)
    TextView tvSettingCacheNum;
    private Unbinder unbinder;

    public SettingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        initData();

        return view;
    }

    private void initData() {
        refreshCacheNum();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initUI() {
        llSettingClearcache.setOnRippleCompleteListener(this);
        llSettingShare.setOnRippleCompleteListener(this);
        llSettingMore.setOnRippleCompleteListener(this);
        llSettingYijianfankui.setOnRippleCompleteListener(this);
        llSettingGengxinrizhi.setOnRippleCompleteListener(this);
        llSettingJianchagengxin.setOnRippleCompleteListener(this);
        llSettingQiurentong.setOnRippleCompleteListener(this);
        llSettingFenxianghaoyou.setOnRippleCompleteListener(this);
        llSettingGuanyuwomen.setOnRippleCompleteListener(this);

    }

    /**
     * 刷新缓存数据
     */
    private void refreshCacheNum() {
        File directory = ImageLoader.getInstance().getDiskCache().getDirectory();

        String size = FileUtils.size(directory);
        tvSettingCacheNum.setText("(" + size + ")");
    }


    /*****
     * 显示不同的toolbar
     *******/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置toolbar显示当前fragment的菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_setting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onComplete(RippleView rippleView) {
        int id = rippleView.getId();
        Log.e("onComplete", id + "");
        switch (id) {
            case R.id.rv_setting_clearcache://清除缓存
                File directory = ImageLoader.getInstance().getDiskCache().getDirectory();
                FileUtils.delFilesFromPath(directory);
                refreshCacheNum();
                break;
            case R.id.rv_setting_share://分享设置
                Intent intent3 = new Intent(getActivity(), CommonActivity.class);
                intent3.putExtra("type",TKContants.Type.SHARE_SETTING);
                startActivity(intent3);
                break;
            case R.id.rv_setting_more://更多设置
                Intent intent4 = new Intent(getActivity(), CommonActivity.class);
                intent4.putExtra("type",TKContants.Type.MORE_SETTING);
                startActivity(intent4);
                break;
            case R.id.rv_setting_yijianfankui://意见反馈
                Intent intent5 = new Intent(getActivity(), CommonActivity.class);
                intent5.putExtra("type",TKContants.Type.YIJIANFANKUI);
                startActivity(intent5);
                break;
            case R.id.rv_setting_gengxinrizhi://获取更新日志
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                intent.putExtra("type",TKContants.Type.UPDATE_LOG);
                startActivity(intent);
                break;
            case R.id.rv_setting_jianchagengxin://检查更新
                checkUpdate();
                break;
            case R.id.rv_setting_qiurentong://到商店去评分
                AppRate.rate(getActivity());
                break;
            case R.id.rv_setting_fenxianghaoyou://分享功能
                showShare();
                break;
            case R.id.rv_setting_guanyuwomen://打开关于我们
                Intent intent2 = new Intent(getActivity(), CommonActivity.class);
                intent2.putExtra("type", TKContants.Type.ABOUT_US);
                startActivity(intent2);
                break;
        }

    }

    /**
     * 检查更新
     */
    private void checkUpdate() {
        ApiClient.getUpdataMsg(getContext(), new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                UpdateBean updateBean = gson.fromJson(s, UpdateBean.class);
                String log = updateBean.getLog();
                String upurl = updateBean.getPhone_apk();
                new AlertDialog.Builder(getContext()).setTitle("升级").setMessage(log)
                        .setPositiveButton("升级", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String savepath = Environment.getExternalStorageDirectory().getAbsolutePath();
                                UpdateUtils.update(getContext(), savepath, upurl);
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }

    /**
     * 分享 判断有没有登录 没有：跳转登录
     *                    有：分享
     */
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是李，这是我的高仿TOOLCOOL");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(getContext());
    }
}
