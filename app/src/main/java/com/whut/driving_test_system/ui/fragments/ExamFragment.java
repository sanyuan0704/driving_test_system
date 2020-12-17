package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private DeductionAdapter deductionAdapter;

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
        //设置分数显示的adapter
        deductionAdapter = new DeductionAdapter();
        binding.iclExamContent.RecDeduction.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.iclExamContent.RecDeduction.setAdapter(deductionAdapter);




        examViewModel.getAllRules(getViewLifecycleOwner(), getContext());//获取规则列表
        my_list = new ArrayList<String>();//提交车况内容


        //扣分后更新视图
        List<Rule> a_rulelist = new ArrayList<>();
        examViewModel.validRules.setValue(a_rulelist);
        examViewModel.validRules.observe(getViewLifecycleOwner(), new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                //设置分数显示的adapter
                deductionAdapter.setValidRules(rules);
                deductionAdapter.notifyDataSetChanged();
            }
        });


        //设置路段选项
        String roadChose = new String();
        examViewModel.roadChose.setValue(roadChose);
        List<String> a_list = new ArrayList<String>();//下拉列表
        a_list.add("路口");
        a_list.add("人行横道");
        a_list.add("学校路段");
        a_list.add("公交车站");
        a_list.add("普通路段");
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRoad.setAdapter(adapter);
        binding.spinnerRoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 选择一个路况
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                examViewModel.roadChose.setValue(adapter.getItem(position).toString());
            }
            //没有选中时的处理
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                Toast.makeText(getContext(), "考试结束", Toast.LENGTH_SHORT).show();
            }
        });


        return binding.getRoot();
    }

    // 设置考试扣分项目展示Adapter
    class DeductionAdapter extends RecyclerView.Adapter<DeductionAdapter.ViewHolder> {
        private List<Rule> validRules = new ArrayList<>();

        public void setValidRules(List<Rule> validRules) {
            this.validRules = validRules;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_deduction, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Rule rule = validRules.get(position);
            holder.deductionContent.setText(rule.content);
            holder.deductionGrade.setText(rule.value);

        }

        @Override
        public int getItemCount() {
            return validRules.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView deductionGrade, deductionContent;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                deductionGrade = itemView.findViewById(R.id.deduction_item);
                deductionContent = itemView.findViewById(R.id.deduction_content);
            }
        }

    }
}
