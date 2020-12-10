package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.MainActivity;
import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentHomeBinding;
import com.whut.driving_test_system.databinding.FragmentLoginBinding;
import com.whut.driving_test_system.models.eneities.User;

/**
 * 实现功能：
 * 1. 考官 / 管理员登录
 */
public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                }else if(binding.rgUsertype.getCheckedRadioButtonId() == R.id.rbtn_admin){
                    user.usertype = User.UserTypes.Admin.ordinal();
                }
                ((MainActivity)getActivity()).mainViewModel.login(user);

                // 跳转
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });
        return binding.getRoot();
    }

}
