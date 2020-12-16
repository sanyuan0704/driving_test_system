package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * 实现功能：
 * 1. 考试指令下发
 * 2. 自动评分（包括车辆行驶过程中的评分和指令操作的评分）
 */
public class ExamFragment extends Fragment {
    private MainViewModel mainViewModel;
    private ExamViewModel examViewModel;
    private FragmentExamBinding binding;

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // viewModel
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);

        // binding
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_exam, container, false);
        binding.setExamViewModel(examViewModel);
        binding.iclExamContent.setExamViewModel(examViewModel);
        binding.setLifecycleOwner(this);

        // 登录判定
        if (mainViewModel.loginedUser.getValue() == null){
            Toast.makeText(getContext(),"警告：用户未登录",Toast.LENGTH_SHORT).show();
            return binding.getRoot();
        }
        examViewModel.examinee.setValue(mainViewModel.selectedExamniee.getValue());

        // TODO: 其他代码写在这条注释以下



        return binding.getRoot();
    }
}
