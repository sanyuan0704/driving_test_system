package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.whut.driving_test_system.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

/**
 * 实现功能：
 * 1. 设置考试规则
 *  1.1 规则阈值设置
 *  1.2 规则别名设置
 * 2. 用户登出
 */
public class SettingsFragment extends Fragment {
    private Button ruleButton, examinerButton;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings_entrance, container, false);
        ruleButton = view.findViewById(R.id.rules_manage_button);
        examinerButton = view.findViewById(R.id.examiner_manage_button);

        ruleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Navigation.findNavController(view1).navigate(R.id.action_settingsFragment_to_ruleSettingFragment);
            }
        });

        examinerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Navigation.findNavController(view2).navigate(R.id.action_settingsFragment_to_examinerSettingFragment);
            }
        });
        return view;
    }
}
