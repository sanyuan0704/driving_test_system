package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentJudgeBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.ExamineeRespository;
import com.whut.driving_test_system.ui.viewmodels.ExamViewModel;
import com.whut.driving_test_system.ui.viewmodels.JudgeViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现功能：
 * 1. 打印成绩单
 * 2. 修改扣分项
 */
public class JudgeFragment extends Fragment {
    private MainViewModel mainViewModel;
    private JudgeViewModel judgeViewModel;
    private FragmentJudgeBinding binding;
    private DeductionAdapter deductionAdapter;

    public JudgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //必要的初始化
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        judgeViewModel = new ViewModelProvider(this).get(JudgeViewModel.class);
        // binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_judge, container, false);
        binding.setLifecycleOwner(this);
        // 记录当前页面位置与初始化
        judgeViewModel.examinee.setValue(mainViewModel.selectedExamniee.getValue());

        //设置分数显示的adapter
        deductionAdapter = new DeductionAdapter();
        binding.ReJudge.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ReJudge.setAdapter(deductionAdapter);

        //刷新考生信息
        judgeViewModel.examinee.observe(getViewLifecycleOwner(), new Observer<Examinee>() {
            @Override
            public void onChanged(Examinee examinee) {
                binding.textView34.setText(examinee.name);
                binding.textView44.setText(examinee.examNumber);
                binding.textView46.setText(examinee.idNumber);
                binding.textView49.setText(examinee.school);
                binding.textView51.setText(examinee.examType);
                //计算一次分数
                List<Rule> rules = mainViewModel.validRules.getValue();
                int sum = 100;
                for (int i = 0; i < rules.size(); i++) {
                    sum -= Integer.parseInt(rules.get(i).value.toString());
                }
                examinee.grade = sum;
                binding.textView53.setText(String.valueOf(examinee.grade));
            }
        });
        //刷新扣分信息
        mainViewModel.validRules.observe(getViewLifecycleOwner(), new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                //设置分数显示
                deductionAdapter.setValidRules(rules);
                deductionAdapter.notifyDataSetChanged();
                //计算一次分数
                int sum = 100;
                for (int i = 0; i < rules.size(); i++) {
                    sum -= Integer.parseInt(rules.get(i).value.toString());
                }
                binding.textView53.setText(String.valueOf(sum));
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //写入成绩
                judgeViewModel.my_ExamineeRespository = new ExamineeRespository(getContext());
                List<Rule> a_rulelist = mainViewModel.validRules.getValue();
                for (int i = 0; i < a_rulelist.size(); i++) {
                    judgeViewModel.my_ExamineeRespository.insertExamnieeRuleRef(judgeViewModel.examinee.getValue(), a_rulelist.get(i));
                }
                //更新考生状态
                Examinee a_examinee = judgeViewModel.examinee.getValue();
                a_examinee.grade = Integer.parseInt(binding.textView53.getText().toString());
                if (a_examinee.grade >= 90) {
                    a_examinee.examStatus = Examinee.ExamStatus.PASSED.ordinal();
                } else {
                    a_examinee.examStatus = Examinee.ExamStatus.FAILED.ordinal();
                }
                judgeViewModel.my_ExamineeRespository.updateExaminees(a_examinee);

                mainViewModel.isExaming.setValue(false);
                Toast.makeText(getContext(), "成绩判断结束", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.action_judgeFragment_to_homeFragment);
            }
        });

        Glide.with(getContext()).load(judgeViewModel.examinee.getValue().imageUrl).into(binding.imageView13);
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_judgededuction, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Rule rule = validRules.get(position);
            holder.deductionContent.setText(rule.content);
            holder.deductionGrade.setText(rule.value);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validRules.remove(position);
                    mainViewModel.validRules.setValue(validRules);
                }
            });
        }

        @Override
        public int getItemCount() {
            return validRules.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView deductionGrade, deductionContent;
            Button delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                deductionGrade = itemView.findViewById(R.id.textView47);
                deductionContent = itemView.findViewById(R.id.textView16);
                delete = itemView.findViewById(R.id.button5);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
