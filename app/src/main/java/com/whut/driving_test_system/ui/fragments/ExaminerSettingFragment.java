package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.ExamerViewModel;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现功能：
 * 1. 通过选择考官编号显示考官信息
 * 2，考官信息的增删改查
 */
public class ExaminerSettingFragment extends Fragment {
    //private Button submitButton;
    //private TextView tvRuleId;
    private EditText etName, etPassName, etPassWord;
    private View view;
    private Spinner sp ;
    private ExamerViewModel examerViewModel;
    private ArrayAdapter<String> adapter;//下拉列表内容

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_examiner_setting, container, false);
        etName=view.findViewById(R.id.EDexamer_name);//姓名
        etPassName=view.findViewById(R.id.EDexamer_passname);//登录名
        etPassWord=view.findViewById(R.id.EDexamer_password);//密码
        sp = (Spinner)view.findViewById(R.id.SP_examer);//编号下拉列表
        examerViewModel=new ViewModelProvider(this).get(ExamerViewModel.class);

        //设置输入框不可编辑
        etName.setFocusable(false);
        etPassName.setFocusable(false);
        etPassWord.setFocusable(false);

        //设置ViewModel
        examerViewModel.setContext(getContext());
        examerViewModel.setLifecycleOwner(getViewLifecycleOwner());

        //刷新下拉名单
        examerViewModel.getAllUsers();
        examerViewModel.my_list.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter = new ArrayAdapter<String>(getContext(), R.layout.cell_examer, R.id.sp_examerid,strings);
                sp.setAdapter(adapter);
                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    // 选择一个考官ID
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        examerViewModel.findAUsers(adapter.getItem(position));
                    }
                    //没有选中时的处理
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });
       //刷新其余信息显示
        examerViewModel.my_user.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                etName.setText(user.name);
                etPassName.setText(user.username);
                etPassWord.setText(user.password);
            }
        });


        return view;
    }


}