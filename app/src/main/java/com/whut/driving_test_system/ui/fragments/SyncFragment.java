package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;

import androidx.fragment.app.Fragment;

/**
 * 实现功能：
 * 1. 上传 / 导入考试信息（包括考官，考生等）
 * 2. 上传 / 导出考试成绩
 */
public class SyncFragment extends Fragment {

    public SyncFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sync, container, false);
    }
}
