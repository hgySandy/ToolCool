package com.example.ljy.subFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
                break;
            case R.id.img_login_weixin:
                Toast.makeText(getActivity(),"微信登录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_login_qq:
                Toast.makeText(getActivity(),"qq登录",Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
