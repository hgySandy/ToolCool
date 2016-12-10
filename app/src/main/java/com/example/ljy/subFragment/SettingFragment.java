package com.example.ljy.subFragment;


import android.content.Intent;
import android.os.Bundle;
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
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.TKContants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinbo.utils.FileUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements OnRippleCompleteListener{

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
        Log.e("onComplete",id+"");
        switch (id){
            case R.id.rv_setting_clearcache:
                //清除缓存
                File directory = ImageLoader.getInstance().getDiskCache().getDirectory();
                FileUtils.delFilesFromPath(directory);
                refreshCacheNum();
                break;
            case R.id.rv_setting_share:
                break;
            case R.id.rv_setting_more:
                break;
            case R.id.rv_setting_yijianfankui:
                break;
            case R.id.rv_setting_gengxinrizhi:
                break;
            case R.id.rv_setting_jianchagengxin:
                break;
            case R.id.rv_setting_qiurentong:
                break;
            case R.id.rv_setting_fenxianghaoyou:
                break;
            case R.id.rv_setting_guanyuwomen:
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                intent.putExtra("type", TKContants.Type.ABOUT_US);
                startActivity(intent);
                break;
        }

    }
}
