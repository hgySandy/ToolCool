package com.example.ljy.subFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljy.toolcool2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class YijianFankuiFragment extends Fragment {


    public YijianFankuiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yijian_fankui, container, false);
        return view;
    }

}
