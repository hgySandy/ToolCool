package com.example.ljy.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.ljy.model.UpdateLogBean;
import com.example.ljy.model.UpdateLogBean.ItemsBean;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.ApiClient;
import com.google.gson.Gson;
import com.xinbo.utils.ResponseListener;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateLogFragment extends Fragment {


    @BindView(R.id.lv_update_content)
    ListView lvUpdateContent;
    private Unbinder unbinder;
    private List<ItemsBean> itemList = new ArrayList<>();
    private CommonAdapter<ItemsBean> itemsCommonAdapter;

    public UpdateLogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_log, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        initData();
        return view;
    }

    private void initData() {
        ApiClient.getUpdateLog(getContext(), new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                UpdateLogBean updateLogBean = gson.fromJson(s, UpdateLogBean.class);
                List<ItemsBean> items = updateLogBean.getItems();
                itemList.clear();
                itemList.addAll(items);
                itemsCommonAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initUI() {
        itemsCommonAdapter = new CommonAdapter<ItemsBean>(getContext(), R.layout.item_update_content, itemList) {

            @Override
            protected void convert(ViewHolder viewHolder, ItemsBean item, int position) {
                viewHolder.setText(R.id.tv_item_update_version, item.getName());
                viewHolder.setText(R.id.tv_item_update_time, item.getDate());
                viewHolder.setText(R.id.tv_item_update_content, item.getLog());
            }
        };

        lvUpdateContent.setAdapter(itemsCommonAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null){
            unbinder.unbind();

        }
    }


}
