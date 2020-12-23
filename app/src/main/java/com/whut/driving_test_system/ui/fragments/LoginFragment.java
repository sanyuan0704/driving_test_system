package com.whut.driving_test_system.ui.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentLoginBinding;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * 实现功能：
 * 1. 考官 / 管理员登录
 */
public class LoginFragment extends Fragment {
    private MainViewModel mainViewModel;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(getActivity());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                final int usertype = (binding.rgUsertype.getCheckedRadioButtonId() == R.id.rbtn_examiner) ? User.UserTypes.EXAMINER.ordinal() : User.UserTypes.ADMIN.ordinal();
                new UserRepository(getContext()).getUserByUsernameAndPassword(username, password).observe(getViewLifecycleOwner(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user == null || usertype != user.usertype) {
                            Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        mainViewModel.loginedUser.setValue(user);
                        if (usertype == User.UserTypes.EXAMINER.ordinal()){
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                        }else if (usertype == User.UserTypes.ADMIN.ordinal()){
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.settingsFragment);
                        }
                    }
                });
            }
        });


        return binding.getRoot();
    }
}
