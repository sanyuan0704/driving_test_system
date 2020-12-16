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
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
            public void onClick(final View v) {
                User user = loginViewModel.user.getValue();
                user.username = binding.etUsername.getText().toString();
                user.password = binding.etPassword.getText().toString();
                Log.d("onLogin", "onClick: " + user.username + ';' + user.password + ';' + user.usertype);
                UserRepository userRepository = new UserRepository(getContext());
                userRepository.getUserByUsernameAndPassword(user.username, user.password).observe(getActivity(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Log.d("onLogin", "user: " + user);
                        if (user == null) {
                            Toast.makeText(getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        mainViewModel.loginedUser.setValue(user);
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_homeFragment);
                    }
                });
            }
        });

        return binding.getRoot();
    }
}
