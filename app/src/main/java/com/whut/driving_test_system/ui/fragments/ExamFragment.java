package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * 实现功能：
 * 1. 考试指令下发
 * 2. 自动评分（包括车辆行驶过程中的评分和指令操作的评分）
 */
public class ExamFragment extends Fragment {
    private ExamViewModel examViewModel;
    private FragmentExamBinding binding;

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_exam, container, false);
        binding.setExamViewModel(examViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
