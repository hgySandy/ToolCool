package com.example.ljy.subFragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    @BindView(R.id.img_setting_logo)
    ImageView imgSettingLogo;
    @BindView(R.id.tv_abouts_url)
    TextView tvAboutsUrl;
    private Unbinder unbinder;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        boolean nightMode = SPUtils.isNightMode(getActivity());
        if (nightMode) {
            imgSettingLogo.setImageResource(R.drawable.logo_trans_night);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_abouts_url)
    public void onClick() {
        //TODO 设置跳转
    }
}
