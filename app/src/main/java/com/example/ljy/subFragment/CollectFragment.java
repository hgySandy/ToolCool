package com.example.ljy.subFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljy.fragment.ArticleContextFragment;
import com.example.ljy.toolcool2.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CollectFragment extends Fragment {

    public CollectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect, container, false);
        FragmentManager fm = getChildFragmentManager();
        ArticleContextFragment fragment = new ArticleContextFragment();
        Bundle args = new Bundle();
        args.putString("collect","collect");
        fragment.setArguments(args);
        fm.beginTransaction().add(R.id.collect_content, fragment).commit();
        return view;
    }


    /***** 显示不同的toolbar *******/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置toolbar显示当前fragment的菜单
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

}

