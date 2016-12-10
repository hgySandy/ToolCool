package com.example.ljy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.ljy.model.SiteContextBean;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.ResponseListener;
import com.xinbo.utils.UILUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SiteFragment extends Fragment {

    @BindView(R.id.expandleLV_site)
    ExpandableListView expandleLVSite;
    private List<SiteContextBean.ItemsBeanX> items = new ArrayList<>();
    private MyExpandableAdapter myExpandableAdapter;
    private String url;
    private Unbinder unbinder;

    public SiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site, container, false);
        initUI(view);
        initData();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        // 清楚之前的menu
        menu.clear();
        // 设置fragment菜单

        inflater.inflate(R.menu.menu_site, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initUI(View view) {
        unbinder = ButterKnife.bind(this, view);
        myExpandableAdapter = new MyExpandableAdapter();
        expandleLVSite.setAdapter(myExpandableAdapter);

    }

    private void initData() {
        url = getArguments().getString("url");
        // Log.e("url", url);
        ApiClient.getWebsite(getActivity(), url, new ResponseListener() {

            @Override
            public void onResponse(String jsondata) {
                // Mylog.show("site:onResponse", jsondata);
                SiteContextBean sitedata = GsonUtils.parseJSON(jsondata, SiteContextBean.class);
                items = sitedata.getItems();
                myExpandableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("site:onErrorResponse", "" + arg0);

            }
        });

    }

    class MyExpandableAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return items.get(groupPosition).getItems().size();
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_site_list, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_item_site_list);
            textView.setText(items.get(groupPosition).getName());

            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                                 ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_site_list_sub, null);
            // 设置显示内容和图片
            TextView textView = (TextView) view.findViewById(R.id.tv_item_site_list_name);
            textView.setText(items.get(groupPosition).getItems().get(childPosition).getName());
            ImageView imageView = (ImageView) view.findViewById(R.id.img_item_site_list_head);
            UILUtils.displayCircleImage(items.get(groupPosition).getItems().get(childPosition).getImage(), imageView);
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

    }
}
