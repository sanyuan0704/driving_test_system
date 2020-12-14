package com.whut.driving_test_system.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private Button addUser, deleteUser, changeUser;
    private EditText etName, etPassName, etPassWord, etId;
    private View view;
    private Spinner sp;
    private ExamerViewModel examerViewModel;
    private ArrayAdapter<String> adapter;//下拉列表内容

    //输入框可输入
    public void edAble() {
        etName.setFocusableInTouchMode(true);
        etName.setFocusable(true);
        etName.setBackgroundColor(Color.WHITE);
        etPassName.setFocusableInTouchMode(true);
        etPassName.setFocusable(true);
        etPassName.setBackgroundColor(Color.WHITE);
        etPassWord.setFocusableInTouchMode(true);
        etPassWord.setFocusable(true);
        etPassWord.setBackgroundColor(Color.WHITE);
    }

    //输入框不可输入
    public void edinAble() {
        etName.setFocusableInTouchMode(false);
        etName.setFocusable(false);
        etName.setBackgroundColor(Color.argb(0, 0, 0, 0));
        etPassName.setFocusableInTouchMode(false);
        etPassName.setFocusable(false);
        etPassName.setBackgroundColor(Color.argb(0, 0, 0, 0));
        etPassWord.setFocusableInTouchMode(false);
        etPassWord.setFocusable(false);
        etPassWord.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_examiner_setting, container, false);
        etName = view.findViewById(R.id.EDexamer_name);//姓名
        etPassName = view.findViewById(R.id.EDexamer_passname);//登录名
        etPassWord = view.findViewById(R.id.EDexamer_password);//密码  三个输入框
        etId = view.findViewById(R.id.EDexamer_id);//增加考官时的输入
        addUser = view.findViewById(R.id.examer_insert);//增加
        deleteUser = view.findViewById(R.id.examer_dellet);//删除
        changeUser = view.findViewById(R.id.examer_updata);//修改      三个按钮
        changeUser.setText("修改");
        addUser.setText("增添");
        sp = (Spinner) view.findViewById(R.id.SP_examer);//编号下拉列表
        examerViewModel = new ViewModelProvider(this).get(ExamerViewModel.class);

        //设置输入框不可编辑
        etName.setFocusable(false);
        etPassName.setFocusable(false);
        etPassWord.setFocusable(false);
        etId.setVisibility(View.GONE);
        etId.setBackgroundColor(Color.WHITE);

        //设置ViewModel
        examerViewModel.setContext(getContext());
        examerViewModel.setLifecycleOwner(getViewLifecycleOwner());

        //刷新下拉名单
        examerViewModel.getAllUsers();
        examerViewModel.my_list.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter = new ArrayAdapter<String>(getContext(), R.layout.cell_examer, R.id.sp_examerid, strings);
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

        //删除按键
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examerViewModel.deleteAUsers();
                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });


        //修改按键
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开放输入权限，换背景色提醒
                if (changeUser.getText() == "修改") {
                    deleteUser.setEnabled(false);
                    addUser.setEnabled(false);
                    edAble();
                    changeUser.setText("保存");
                } else {
                    deleteUser.setEnabled(true);
                    addUser.setEnabled(true);
                    examerViewModel.changeAUsers(etName.getText().toString(), etPassName.getText().toString()
                            , etPassWord.getText().toString());
                    edinAble();
                    changeUser.setText("修改");
                    Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //增添按键
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addUser.getText() == "增添") {
                    deleteUser.setEnabled(false);
                    changeUser.setEnabled(false);
                    edAble();
                    etId.setVisibility(View.VISIBLE);
                    addUser.setText("确定");
                } else {
                    deleteUser.setEnabled(true);
                    changeUser.setEnabled(true);
                    Boolean flag = examerViewModel.addAUsers(etId.getText().toString(), etName.getText().toString(),
                            etPassName.getText().toString(), etPassWord.getText().toString());
                    if (flag) {
                        Toast.makeText(getContext(), "增添成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "增添失败", Toast.LENGTH_SHORT).show();
                    }
                    edinAble();
                    etId.setVisibility(View.GONE);
                    addUser.setText("增添");
                }
            }
        });

        return view;
    }


}