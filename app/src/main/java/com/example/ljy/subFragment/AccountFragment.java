package com.example.ljy.subFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ljy.toolcool2.R;
import com.xinbo.utils.UILUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    @BindView(R.id.img_account_head)
    ImageView imgAccountHead;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_accout_weibo)
    TextView tvAccoutWeibo;
    private Unbinder unbinder;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Platform weibo = ShareSDK.getPlatform(getContext(), SinaWeibo.NAME);
        String token = weibo.getDb().getToken();
        if (!token.equals("")) {
            PlatformDb accountinfo = weibo.getDb();
            String userIcon = accountinfo.getUserIcon();
            String userName = accountinfo.getUserName();
            UILUtils.displayImage(userIcon, imgAccountHead);
            tvAccountName.setText(userName);
            tvAccoutWeibo.setText(userName);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
