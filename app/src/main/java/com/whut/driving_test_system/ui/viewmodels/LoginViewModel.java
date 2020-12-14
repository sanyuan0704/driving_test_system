package com.whut.driving_test_system.ui.viewmodels;

import android.view.View;

import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<User> user;
    private boolean isLogin;

    public LoginViewModel() {
        this.user = new MutableLiveData<>(new User("","","","",0));
        this.isLogin = false;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void onChangeUserType(View v) {
        User user = this.user.getValue();
        user.usertype = user.usertype == User.UserTypes.EXAMINER.ordinal() ? User.UserTypes.ADMIN.ordinal() : User.UserTypes.EXAMINER.ordinal();
        this.user.setValue(user);
    }
}
