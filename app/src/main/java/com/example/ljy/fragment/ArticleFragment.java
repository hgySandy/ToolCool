package com.example.ljy.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ljy.toolcool2.CommonActivity;
import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.TKContants;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    @BindView(R.id.tab_article_indicator)
    SmartTabLayout smarttab;
    @BindView(R.id.viewpager_article_context)
    ViewPager viewpagerContext;
    private View view;
    private Unbinder unbinder;
    private String[] title={"热门","推荐","科技","创业","数码","技术","设计","营销"};


    /***** 覆盖Acitvity的toolbar *******/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ArticleFragment","onCreate");
        // 设置toolbar显示当前fragment的菜单
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("ArticleFragment","onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_article, null);
            unbinder = ButterKnife.bind(this, view);
            initUI();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ArticleFragment","onDestroy");
        unbinder.unbind();
    }


    private void initUI() {
        //设置viewpager内容
        viewpagerContext.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        //设置smartindiactor对应的viewpager
        smarttab.setViewPager(viewpagerContext);


    }
    //用fragmentStatePagerAdapter 适用5个及以上的fragment,保存前后一个以及自己三个对象，超出的话会destroy。
    private final class MyPagerAdapter extends FragmentPagerAdapter {
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        private MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("Fragment",""+position);
            ArticleContextFragment articleContextFragment=new ArticleContextFragment();
            Log.e("articleContextFragment",articleContextFragment.toString());
            Bundle args = new Bundle();
            args.putInt("position", position);
            articleContextFragment.setArguments(args);
            return articleContextFragment;
        }
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_article,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_search_main:
                Intent intent = new Intent(getActivity(), CommonActivity.class);
                intent.putExtra("type", TKContants.Type.SEARCH);
                startActivity(intent);
                Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
