package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;

/**
 * 实现功能：
 * 1. 考生基本确认
 * 2. 展示以往考试记录
 * 3. 拍照对比证件照并确认
 */
public class VerifyFragment extends Fragment {


    public VerifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify, container, false);
    }

}
