package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
    private ArrayAdapter<String> adapter;//下拉列表内容
    private List<String> my_list;//提交车况内容

    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // viewModel
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam, container, false);

        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam, container, false);
        binding.setExamViewModel(examViewModel);
        binding.iclExamContent.setExamViewModel(examViewModel);
        binding.setLifecycleOwner(this);

        // 记录当前页面位置与初始化
        mainViewModel.isExaming.setValue(true);
        mainViewModel.anchor.setValue(R.id.examFragment);
        examViewModel.examinee.setValue(mainViewModel.selectedExamniee.getValue());

        // TODO: 其他代码写在这条注释以下

        //设置路段选项
        List<String> a_list = new ArrayList<String>();//下拉列表
        a_list.add("路口");
        a_list.add("人行横道");
        a_list.add("学校路段");
        a_list.add("公交车站");
        a_list.add("普通路段");
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, a_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRoad.setAdapter(adapter);


        examViewModel.getAllRules(getViewLifecycleOwner(), getContext());//获取规则列表
        my_list = new ArrayList<String>();//提交车况内容

        //检测规则列表，自动评分 点击按钮后
        examViewModel.my_Rule.observe(getViewLifecycleOwner(), new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                binding.floatingActionButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        my_list.clear();
                        my_list.add(binding.ET1.getText().toString());
                        my_list.add(binding.ET2.getText().toString());
                        my_list.add(binding.ET3.getText().toString());
                        my_list.add(binding.ET4.getText().toString());
                        my_list.add(binding.ET5.getText().toString());
                        my_list.add(binding.ET6.getText().toString());
                        my_list.add(binding.ET7.getText().toString());
                        binding.spinnerRoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            // 选择一个路况
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                my_list.add(adapter.getItem(position));
                            }

                            //没有选中时的处理
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        //完成对输入内容的读取，进行自动评判
                        examViewModel.autoExamFunction(getContext(), my_list, binding);
                    }
                });

            }
        });

        // 设置结束考试状态
        binding.iclExamContent.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.isExaming.setValue(false);
                Toast.makeText(getContext(),"考试结束",Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}
