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
public class MoreSettingFragment extends Fragment {


    public MoreSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_setting, container, false);


        return view;
    }

}
