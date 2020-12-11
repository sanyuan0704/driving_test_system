package com.whut.driving_test_system.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.CellRuleBinding;
import com.whut.driving_test_system.databinding.FragmentRuleSettingBinding;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;
import com.whut.driving_test_system.ui.viewmodels.RuleViewModel;
import com.whut.driving_test_system.util.UnifyAdapter;

import java.util.ArrayList;
import java.util.List;

public class RuleSettingFragment extends Fragment {
    private FragmentRuleSettingBinding binding;
    final int variableId = BR.rule;
    private  List<Rule> list = new ArrayList<>();
    private RuleViewModel ruleViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rule_setting, container, false);
        binding.setLifecycleOwner(getActivity());
        ruleViewModel = ViewModelProviders.of(this).get(RuleViewModel.class);

        final UnifyAdapter<Rule> ruleAdapter = new UnifyAdapter<Rule>(R.layout.cell_rule, variableId, list) {
            @Override
            public void convert(UnifyAdapter<Rule>.ViewHolder holder, Rule rule) {
                holder.setBinding(variableId, rule);
            }
        };
        binding.ruleList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.ruleList.setAdapter(ruleAdapter);

        ruleViewModel.insertRule();
        ruleViewModel.getAllRuleLive().observe(getActivity(), new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                System.out.println("列表长度:" + rules.size());
                ruleAdapter.setList(rules);
                ruleAdapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }


}
