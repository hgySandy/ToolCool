package com.example.ljy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljy.model.AccessTokenKeeper;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ImageLoadUtils;
import com.example.ljy.utils.SPUtils;
import com.example.ljy.utils.TKContants;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.legacy.AccountAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.xinbo.utils.UILUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.img_mine_head)
    ImageView imgMineHead;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.linear_mine_login)
    LinearLayout linearMineLogin;
    @BindView(R.id.list_mine)
    ListView listMine;

    private List<String> itemnames = new ArrayList<>();
    private Unbinder unbinder;
    private Oauth2AccessToken mAccessToken;
    private UsersAPI mUsersAPI;
    private String mUIDstr;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initUI(view);
        initData();
        return view;
    }

    private void initData() {
        //获取登录的Token信息
        mAccessToken = AccessTokenKeeper.readAccessToken(getActivity());
        mUIDstr = mAccessToken.getUid();
//        String loginImageUrl = SPUtils.getLoginImageUrl(getActivity());
        //如果本地有uid信息：如果有保存本地图片则显示本地图片，没有的话使用用户信息接口获取图片
        if (!mUIDstr.equals("")) {
//            if (loginImageUrl == null) {
                //获取用户信息接口
                mUsersAPI = new UsersAPI(getActivity(), TKContants.WBCONANTS.APP_KEY, mAccessToken);
                //获取用户uid
                long uid = Long.parseLong(mUIDstr);
            Log.e("asefsdf","11");
                //请求数据在mlistener回调方法内处理数据
            mUsersAPI.show(uid, mListener);

//
//            } else {
//                UILUtils.displayImage(loginImageUrl, imgMineHead);
//            }

        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // 清楚之前的menu
        menu.clear();
        // 设置fragment菜单
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initUI(View view) {
        unbinder = ButterKnife.bind(this, view);
        itemnames.clear();
        itemnames.add("我的待读");
        itemnames.add("我的收藏");
        itemnames.add("我的推刊");
        itemnames.add("我的通知");

        /******************/
        /**** listview ****/
        /******************/
        settingAdapter settingAdapter = new settingAdapter(getActivity(), R.layout.item_mine_list, itemnames);
        listMine.setAdapter(settingAdapter);
        listMine.setOnItemClickListener(this);


    }

    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
                // 调用 User#parse 将JSON串解析成User对象
                User user = User.parse(response);
                Log.e("asefsdf","11");
                //保存图片路径
                String profile_image_url = user.profile_image_url;
                UILUtils.displayImage(profile_image_url, imgMineHead);
                SPUtils.saveLoginImageUrl(getActivity(), profile_image_url);
                UILUtils.displayImage(profile_image_url, imgMineHead);
                //保存名字
//                user.name
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };


    @OnClick(R.id.linear_mine_login)
    public void onClick() {
        if (mUIDstr.equals("")) {
            //跳转登录界面
            Intent intent = new Intent(getActivity(), CommonActivity.class);
            intent.putExtra("type", TKContants.Type.LOGIN);
            startActivity(intent);
        }

    }


    private class settingAdapter extends CommonAdapter<String> {

        public settingAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, String item, int position) {
            viewHolder.setText(R.id.tv_item_mine_content, item);
            ImageView imgView = viewHolder.getView(R.id.img_item_mine_setting);
            imgView.setImageResource(TKContants.DRAWABLE.MINE_SETTING[position]);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final int my_daidu = 0;
        final int my_collect = 1;
        switch (position) {
            case my_daidu:
                break;
            case my_collect:
                Toast.makeText(getActivity(), "点击收藏", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), CommonActivity.class);
//                intent.putExtra("type", TKContants.Type.COLLECT);
//                startActivity(intent);
                break;

            default:
                break;
        }

    }

}
