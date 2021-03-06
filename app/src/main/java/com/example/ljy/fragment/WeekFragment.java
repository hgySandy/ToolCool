package com.example.ljy.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.android.volley.VolleyError;
import com.example.ljy.model.WeekContextBean;
import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.example.ljy.utils.TKContants;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.ResponseListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends Fragment {
    private MyExpandableAdapter myExpandableAdapter;
    private String url;
    private List<WeekContextBean.ItemsBeanX> items = new ArrayList<>();
    private ExpandableListView expandableListView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 设置toolbar显示当前fragment的菜单
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		Log.e("WeeklyFragment", "url:"+getArguments().getString("url"));
//		Log.e("WeeklyFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        initUI(view);
        initData();
        return view;
    }

    public WeekFragment() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // 清楚之前的menu
        menu.clear();
        // 设置fragment菜单
        inflater.inflate(R.menu.menu_week, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_week_setting:
                Toast.makeText(getActivity(), "点击了设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    private void initUI(View view) {
        // Log.e("view", ""+view);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandleLV_weekly);
        expandableListView.setGroupIndicator(null);
        myExpandableAdapter = new MyExpandableAdapter();
        items.clear();//清空items内的数据，否则第二次进入站点会报错
        expandableListView.setAdapter(myExpandableAdapter);
        //展开不能收缩
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

    }

    private void initData() {
//        url = getArguments().getString("url");
        ApiClient.getWeekly(getActivity(), new ResponseListener() {

            @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onResponse(String jsondata) {
                WeekContextBean weekdata = GsonUtils.parseJSON(jsondata, WeekContextBean.class);
                items = weekdata.getItems();
                myExpandableAdapter.notifyDataSetChanged();
                int count = expandableListView.getCount();
                for (int i = 0; i < count; i++) {
                    expandableListView.expandGroup(i, false);
                }


            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

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
            TextView textView = (TextView) view.findViewById(R.id.tv_item_weekly_list);
            textView.setText(items.get(groupPosition).getName());
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
            String childID = items.get(groupPosition).getItems().get(childPosition).getId();
            String parentTitle = items.get(groupPosition).getName();
            String childTitle = items.get(groupPosition).getItems().get(childPosition).getTitle();
            Long time = items.get(groupPosition).getItems().get(childPosition).getTime();

            RippleView rippleView = (RippleView) view.findViewById(R.id.ripple_week_item_list_context);
            rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    //跳转周刊详情页面

                    Intent intent = new Intent(getActivity(), CommonActivity.class);
                    intent.putExtra("type", TKContants.Type.DETAIL_WEEKLY_FRAGMENT);
                    intent.putExtra("title", parentTitle+"-"+childTitle);
                    intent.putExtra("id",childID);
                    startActivity(intent);
                }
            });
            //有时间做一下优化
            // 设置显示内容
            TextView tv_name = (TextView) view.findViewById(R.id.tv_item_weekly_list_name);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_item_weekly_list_time);
            tv_name.setText(childTitle);
            String datatime = getDateTimeFromMillisecond(time);

            tv_time.setText(datatime);

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

    public String getDateTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

}
