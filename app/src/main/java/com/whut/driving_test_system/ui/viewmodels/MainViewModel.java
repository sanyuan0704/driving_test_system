package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public User user;

    public boolean login(User user) {
        // TODO: login process
        return true;
    }

    public boolean isLogin(){
        return user != null;
    }
}
