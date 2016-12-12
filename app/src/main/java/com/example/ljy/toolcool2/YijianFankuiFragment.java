package com.example.ljy.toolcool2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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


        // Inflate the lan  yout for this fragment
        View view = inflater.inflate(R.layout.fragment_yijian_fankui, container, false);
        return view;
    }

}
