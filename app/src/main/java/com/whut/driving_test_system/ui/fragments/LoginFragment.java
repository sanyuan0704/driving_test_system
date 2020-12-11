package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentLoginBinding;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * 实现功能：
 * 1. 考官 / 管理员登录
 */
public class LoginFragment extends Fragment {
    LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        final FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLifecycleOwner(getActivity());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.username = binding.etUsername.getText().toString();
                user.password = binding.etPassword.getText().toString();
                if (binding.rgUsertype.getCheckedRadioButtonId() == R.id.rbtn_examiner) {
                    user.usertype = User.UserTypes.Examiner.ordinal();
                } else if (binding.rgUsertype.getCheckedRadioButtonId() == R.id.rbtn_admin) {
                    user.usertype = User.UserTypes.Admin.ordinal();
                }

                if (!loginViewModel.login(user)) {
                    Toast.makeText(LoginFragment.this.getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 跳转
                Toast.makeText(LoginFragment.this.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 已经登录过了就不用再登录了
        if (loginViewModel.isLogin()) {
            NavController controller = Navigation.findNavController(getView());
            controller.navigate(R.id.action_loginFragment_to_homeFragment);
        }
    }
}
