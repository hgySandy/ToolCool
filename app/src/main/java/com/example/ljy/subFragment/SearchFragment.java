package com.example.ljy.subFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.ljy.fragment.ArticleContextFragment;
import com.example.ljy.model.SearchHistory;
import com.example.ljy.toolcool2.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
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
public class SearchFragment extends Fragment {
    @BindView(R.id.smarttab_search)
    SmartTabLayout smarttabSearch;
    @BindView(R.id.viewpager_search_context)
    ViewPager viewpagerSearchContext;
    @BindView(R.id.lv_search_result)
    LinearLayout lvSearchResult;
    @BindView(R.id.lv_search_history)
    ListView lvSearchHistory;
    private Unbinder unbinder;


    private String[] title = {"热门", "主题", "站点"};
    private String searchcontext;
    private SearchView searchView;

    private SearchResultAdapter searchReslutAdapter;
    private SearchHistoryAdapter searchHistoryAdapter;

    private List<SearchHistory> execute = new ArrayList<>();


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initUI(view);
        initHistoryData();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initHistoryData() {
        execute.clear();
        execute.addAll(new Select().from(SearchHistory.class).execute());
    }

    public void initUI(View view) {
        unbinder = ButterKnife.bind(this, view);
        /**** 1.搜索结果页面 ******/
        searchReslutAdapter = new SearchResultAdapter(getChildFragmentManager());
        viewpagerSearchContext.setAdapter(searchReslutAdapter);
        // 设置smartindiactor
        smarttabSearch.setViewPager(viewpagerSearchContext);
        /**** 2.搜索记录页面 ******/
        searchHistoryAdapter = new SearchHistoryAdapter(getActivity(), R.layout.item_search_list, execute);
        lvSearchHistory.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.item_search_footview, null));
        lvSearchHistory.setAdapter(searchHistoryAdapter);
        lvSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                int size = execute.size();
//                Log.e("onItemClick", "size:" + size + "position:" + position);
                if (position == size) {//删除搜索记录
                    for (int i = 0; i < size; i++) {
                        initHistoryData();
//                        Log.e("execute", execute.size() + "");
                        String name = execute.get(0).getName();
                        new Delete().from(SearchHistory.class).where("name=?", name).execute();
                    }
                    initHistoryData();
                    searchHistoryAdapter.notifyDataSetChanged();
                } else {//查询
                    searchView.setQuery(execute.get(position).getName(), true);
                }

            }
        });
        lvSearchResult = (LinearLayout) view.findViewById(R.id.lv_search_result);

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
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search_search));
        // 设置直接跳转搜索界面
        searchView.setIconified(false);
        // super.onCreateOptionsMenu(menu, inflater);
        /***** searchview *******/
        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
//                Log.e("setOnQueryTextListener", query);
//                 向数据库写入数据,避免重复
                boolean exist = false;
                for (int i = 0; i < execute.size(); i++) {
                    String name = execute.get(i).getName();
                    if (name.equals(query)) {
                        exist = true;
                    }
                }
                if (!exist) {
                    SearchHistory search = new SearchHistory(query);
                    search.save();
                }
                // 更新DB数据
                initHistoryData();
                searchHistoryAdapter.notifyDataSetChanged();
                //更新数据
                searchcontext = query;
                //取消焦点
                searchView.clearFocus();
                searchView.setFocusable(false);
                searchReslutAdapter.notifyDataSetChanged();
//                Log.e("SearchFragment", "searchcontext:" + searchcontext);
//				Log.e("SearchFragment", "searchReslutAdapter:"+searchReslutAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                Log.e("setOnQueryTextListener", newText);
                lvSearchResult.setVisibility(View.GONE);
                lvSearchHistory.setVisibility(View.VISIBLE);
                // TODO 有时间可以做过滤
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                lvSearchResult.setVisibility(View.GONE);
                lvSearchHistory.setVisibility(View.VISIBLE);
            } else {
                if (searchcontext != null) {
                    lvSearchResult.setVisibility(View.VISIBLE);
                    lvSearchHistory.setVisibility(View.GONE);

                }
            }

        });
        // 设置true 覆盖默认动作
        searchView.setOnCloseListener(() -> {
            // 设置true 覆盖默认动作
            return true;
        });

    }

    // 用fragmentStatePagerAdapter
    // 适用5个及以上的fragment，创建后的fragmen会ondestroyview,但是不会销毁。
    private final class SearchResultAdapter extends FragmentStatePagerAdapter {
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        private SearchResultAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Fragment getItem(int position) {
            ArticleContextFragment fragment = new ArticleContextFragment();
            Bundle args = new Bundle();
            args.putString("search", searchcontext);
            args.putInt("position", position);
//            Log.e("searchReslutAdapter", "searchcontext" + searchcontext + "position" + position);
            fragment.setArguments(args);
            return fragment;
        }
    }

    private class SearchHistoryAdapter extends CommonAdapter<SearchHistory> {

        public SearchHistoryAdapter(Context context, int layoutId, List<SearchHistory> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder viewHolder, SearchHistory item, int position) {

            viewHolder.setText(R.id.tv_search_list_context, item.getName());

        }
    }

}
