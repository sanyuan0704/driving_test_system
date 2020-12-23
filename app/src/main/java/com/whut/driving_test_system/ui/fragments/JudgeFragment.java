package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
       View view = inflater.inflate(R.layout.fragment_judge, container, false);

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_judge, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
