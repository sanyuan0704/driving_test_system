package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<User> user;

    public LoginViewModel() {
        this.user = new MutableLiveData<>();
    }

    public boolean login(User user) {
        // TODO: login process
        this.user.setValue(user);
        return true;
    }

    public boolean isLogin() {
        return user.getValue() != null;
    }
}
