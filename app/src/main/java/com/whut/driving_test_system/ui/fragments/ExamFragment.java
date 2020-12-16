package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现功能：
 * 1. 考试指令下发
 * 2. 自动评分（包括车辆行驶过程中的评分和指令操作的评分）
 */
public class ExamFragment extends Fragment {
    private ExamViewModel examViewModel;
    private FragmentExamBinding binding;
    private ArrayAdapter<String> adapter;//下拉列表内容

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
        binding.iclExamContent.setExamViewModel(examViewModel);
        binding.setLifecycleOwner(this);
        //获取控件
        List<String> a_list = new ArrayList<String>();
        a_list.add("路口");
        a_list.add("人行横道");
        a_list.add("学校路段");
        a_list.add("公交车站");
        a_list.add("普通路段");
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, a_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRoad.setAdapter(adapter);









        return binding.getRoot();
    }
}
