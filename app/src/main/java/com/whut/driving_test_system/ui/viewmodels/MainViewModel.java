package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<User> loginedUser;   // 已登录的用户（考官或管理员）
    public MutableLiveData<Examinee> selectedExamniee;  // 被选中的考生


    public MainViewModel() {
        this.loginedUser = new MutableLiveData<>();
        this.selectedExamniee = new MutableLiveData<>();
    }

}
