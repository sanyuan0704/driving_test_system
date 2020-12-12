package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentRuleSettingBinding;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.ui.viewmodels.RuleViewModel;

import java.util.ArrayList;
import java.util.List;

public class RuleSettingFragment extends Fragment {
    private FragmentRuleSettingBinding binding;
    private  List<Rule> list = new ArrayList<>();
    private RuleViewModel ruleViewModel;
    private RuleAdapter ruleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rule_setting, container, false);
        binding.setLifecycleOwner(getActivity());
        // 处理数据绑定 ruleList
        ruleViewModel = ViewModelProviders.of(this).get(RuleViewModel.class);
        ruleAdapter = new RuleAdapter(list);
        binding.ruleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.ruleList.setAdapter(ruleAdapter);

        ruleViewModel.getAllRuleLive().observe(getActivity(), new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                ruleAdapter.setList(rules);
                ruleAdapter.notifyDataSetChanged();
            }
        });

        // 处理编辑提交



        return binding.getRoot();
    }

    class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.RuleViewHolder> {
        public List<Rule> getList() {
            return list;
        }

        public void setList(List<Rule> list) {
            this.list = list;
        }

        private List<Rule> list;
        final int variableId = BR.rule;


        public RuleAdapter(List<Rule> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RuleAdapter.RuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_rule, parent, false);
            return new RuleAdapter.RuleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RuleAdapter.RuleViewHolder holder, int position) {
            holder.setBinding(variableId, list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        protected class RuleViewHolder extends RecyclerView.ViewHolder {
            private Button submitButton;
            private TextView tvRuleId;
            private EditText etNickname, etContent, etValue, etFirstKey, etFirstValue, etSecondKey, etSecondValue;
            private Switch swAuto, swSelected;
            private View itemView;

            private ViewDataBinding mBinding;

            public RuleViewHolder(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                mBinding = DataBindingUtil.bind(itemView);
                handleElements();
                bindingSubmitEvent();
            }

            private void bindingSubmitEvent() {
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ruleId = tvRuleId.getText().toString();
                        String nickname = etNickname.getText().toString();
                        String content = etContent.getText().toString();
                        String value = etValue.getText().toString();
                        String firstKey = etFirstKey.getText().toString();
                        String firstValue = etFirstValue.getText().toString();
                        String secondKey = etSecondKey.getText().toString();
                        String secondValue = etSecondValue.getText().toString();
                        Boolean is_auto = swAuto.isChecked();
                        Boolean is_selected = swSelected.isChecked();
                        Rule rule = new Rule(ruleId, content, nickname, value, is_auto, is_selected, firstKey, firstValue, secondKey, secondValue);
                        ruleViewModel.updateRule(rule);
                        Toast.makeText(getContext(), "保存成功!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void handleElements() {
                View view = itemView;
                tvRuleId = view.findViewById(R.id.rule_id);
                submitButton = view.findViewById(R.id.rule_edit_button);
                etNickname = view.findViewById(R.id.rule_nickname);
                etContent = view.findViewById(R.id.rule_content);
                etValue = view.findViewById(R.id.rule_value);
                etFirstKey = view.findViewById(R.id.rule_first_threshold_key);
                etFirstValue = view.findViewById(R.id.rule_first_threshold_value);
                etSecondKey = view.findViewById(R.id.rule_second_threshold_key);
                etSecondValue = view.findViewById(R.id.rule_second_threshold_value);
                swAuto = view.findViewById(R.id.is_auto);
                swSelected = view.findViewById(R.id.is_select);
                submitButton = view.findViewById(R.id.rule_edit_button);
            }
            public RuleAdapter.RuleViewHolder setBinding(int variableId , Object object){
                mBinding.setVariable(variableId , object);
                mBinding.executePendingBindings();
                return this;
            }
        }
    }


}
