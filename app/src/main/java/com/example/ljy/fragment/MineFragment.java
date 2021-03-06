package com.example.ljy.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.MainActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.TKContants;
import com.xinbo.utils.UILUtils;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

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
    private Platform weibo;
    private String token;

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
        initWeibo();
    }

    private void initWeibo() {

        weibo = ShareSDK.getPlatform(getContext(), SinaWeibo.NAME);
        token = weibo.getDb().getToken();
        if (!token.equals("")) {
            PlatformDb accountinfo = weibo.getDb();
            String userName = accountinfo.getUserName();
            String userIcon = accountinfo.getUserIcon();
            UILUtils.displayImage(userIcon, imgMineHead);
            tvMineName.setText(userName);
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
        if (unbinder!=null){
            unbinder.unbind();

        }
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


    @OnClick(R.id.linear_mine_login)
    public void onClick() {
        Intent intent = new Intent(getActivity(), CommonActivity.class);
        if (!token.equals("")) {
            intent.putExtra("type", TKContants.Type.ACCOUNT_INFO);
        } else {
            intent.putExtra("type", TKContants.Type.LOGIN);
        }
        startActivity(intent);


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
        final int my_tongzhi =3;
        switch (position) {
            case my_tongzhi:
                break;
            case my_collect:
//                Toast.makeText(getActivity(), "点击收藏", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                intent.putExtra("type", TKContants.Type.COLLECT);
                startActivity(intent);
                break;

            default:
                Intent intent2 = new Intent(getActivity(), CommonActivity.class);
                intent2.putExtra("type", TKContants.Type.COLLECT);
                startActivity(intent2);

                break;
        }

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // 清楚之前的menu
        menu.clear();
        if (!token.equals("")){
            getActivity().getMenuInflater().inflate(R.menu.menu_mine, menu);
        }
        // 设置fragment菜单
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.action_zhuxiao:
                weibo.removeAccount();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}
