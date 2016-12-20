package com.example.ljy.subFragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.android.volley.VolleyError;
import com.example.ljy.model.WeekContextBean;
import com.example.ljy.model.WeeklyDetailBean;
import com.example.ljy.model.WeeklyDetailBean.ItemsBeanX;
import com.example.ljy.model.WeeklyDetailBean.ItemsBeanX.ItemsBean;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.TKContants;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.ResponseListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekDetailFragment extends Fragment {

    View view;
    List<ItemsBeanX> items=new ArrayList<>();
    MyExpandableAdapter myExpandableAdapter;

    @BindView(R.id.expandleLV_weekly_Detail)
    ExpandableListView expandleLVWeeklyDetail;
    private Unbinder unbinder;


    public WeekDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_week_detail, container, false);
            unbinder = ButterKnife.bind(this, view);
            initUI();
            initData();
        }
        return view;
    }

    private void initData() {
        //下载数据
        String chickID = getArguments().getString("id");
        ApiClient.getWeeklyDetail(getActivity(),chickID, new ResponseListener() {

            @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onResponse(String jsondata) {
                Log.e("weekdetail:onResponse", jsondata);
                WeeklyDetailBean weeklyDetail = GsonUtils.parseJSON(jsondata, WeeklyDetailBean.class);
                items.clear();
                items.addAll(weeklyDetail.getItems());
                myExpandableAdapter.notifyDataSetChanged();
                //展开
                int count = expandleLVWeeklyDetail.getCount();
                for (int i = 0; i < count; i++) {
                  expandleLVWeeklyDetail.expandGroup(i, false);
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("weekdetail:onError", "" + arg0);

            }
        });
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    private void initUI() {
        myExpandableAdapter = new MyExpandableAdapter();
        expandleLVWeeklyDetail.setAdapter(myExpandableAdapter);
        expandleLVWeeklyDetail.setGroupIndicator(null);
        expandleLVWeeklyDetail.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
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
            return items.get(groupPosition).getItems().size();
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_week_list, null);
            //设置组标题
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_item_weekly_list);
            tvTitle.setText(items.get(groupPosition).getName());
            TextView tvMore = (TextView) view.findViewById(R.id.tv_item_weekly_list_right);
            tvMore.setText("");
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                                 ViewGroup parent) {
            View view;
            if (convertView!=null){
                view=convertView;
            }else {
                view = getActivity().getLayoutInflater().inflate(R.layout.item_week_list_sub, null);
            }

            ItemsBean item = items.get(groupPosition).getItems().get(childPosition);
            String title = item.getTitle();


            //查找控件
            TextView tvName = (TextView) view.findViewById(R.id.tv_item_weekly_list_name);
            TextView tvTime = (TextView) view.findViewById(R.id.tv_item_weekly_list_time);
            RippleView ripple = (RippleView) view.findViewById(R.id.ripple_week_item_list_context);
            ripple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    //跳转详情页面
                    Intent intent = new Intent(getActivity(), CommonActivity.class);
                    intent.putExtra("collecttitle",item.getTitle());
                    intent.putExtra("urlID", item.getUrl());
                    intent.putExtra("type", TKContants.Type.DETAIL_ARTICL_FRAGMENT);
                    startActivity(intent);

                }
            });
            tvTime.setText("");
            tvName.setText(title);
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
