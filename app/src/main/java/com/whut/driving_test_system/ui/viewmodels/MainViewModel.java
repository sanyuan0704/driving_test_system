package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    public MutableLiveData<User> loginedUser;   // 已登录的用户（考官或管理员）
    public MutableLiveData<Examinee> selectedExamniee;  // 被选中的考生
    public MutableLiveData<Boolean> isExaming;  // 是否在考试中
    public MutableLiveData<Integer> anchor; // 当前页面id
    public MutableLiveData<List<Rule>> validRules;//扣分列表

    public MainViewModel() {
        this.loginedUser = new MutableLiveData<>();
        this.selectedExamniee = new MutableLiveData<>();
        this.isExaming = new MutableLiveData<>(false);
        this.anchor = new MutableLiveData<>(R.id.welcomeFragment);
        this.validRules = new MutableLiveData<List<Rule>>();
    }

}
