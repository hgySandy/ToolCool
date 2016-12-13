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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ljy.toolcool2.MainActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.SPUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.img_login_xinlang)
    ImageView imgLoginXinlang;
    @BindView(R.id.img_login_weixin)
    ImageView imgLoginWeixin;
    @BindView(R.id.img_login_qq)
    ImageView imgLoginQq;
    private Unbinder unbinder;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initUI(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initUI(View view) {
        unbinder = ButterKnife.bind(this, view);
        //判断是否夜间模式设置Logo
        boolean nightMode = SPUtils.isNightMode(getActivity());
        if (nightMode) {
            imgLogo.setImageResource(R.drawable.logo_trans_night);
        } else {
            imgLogo.setImageResource(R.drawable.logo_trans);
        }


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
        getActivity().getMenuInflater().inflate(R.menu.menu_login, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick({R.id.img_login_xinlang, R.id.img_login_weixin, R.id.img_login_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_login_xinlang:
                loginXinlangweibo();
                break;
            case R.id.img_login_weixin:
                Toast.makeText(getActivity(), "微信登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_login_qq:
                Toast.makeText(getActivity(), "qq登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loginXinlangweibo() {
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        weibo.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                arg0.getDb().exportData();
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
        //authorize与showUser单独调用一个即可
        weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
//        weibo.showUser(null);//授权并获取用户信息
        //移除授权
        //weibo.removeAccount(true);
    }


}
