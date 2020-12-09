package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;

/**
 * 实现功能：
 * 1. 打印成绩单
 * 2. 修改扣分项
 */
public class JudgeFragment extends Fragment {


    public JudgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_judge, container, false);
    }

}
