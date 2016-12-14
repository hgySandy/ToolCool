package com.example.ljy.subFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

import com.example.ljy.toolcool2.R;
import com.example.ljy.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreSettingFragment extends Fragment implements OnCheckedChangeListener {


    @BindView(R.id.swtich_moresetting_onlywifi)
    SwitchCompat swtichOnlywifi;
    @BindView(R.id.spinner_moresetting_zihao)
    AppCompatSpinner spinnerZihao;
    private Unbinder unbinder;

    String[] strs = {"最小", "偏小", "中等", "偏大", "最大"};

    public MoreSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        //设置仅wifi下载
        swtichOnlywifi.setChecked(SPUtils.isOnlyWifiImageLoadMode(getActivity()));
        swtichOnlywifi.setOnCheckedChangeListener(this);
        spinnerZihao.setAdapter(new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, strs));
        spinnerZihao.setSelection(SPUtils.getTextSize(getActivity()));
        spinnerZihao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SPUtils.saveTextSize(getActivity(),position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SPUtils.saveWifiImageLoadMode(getActivity(), isChecked);
    }
}
