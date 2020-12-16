package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<User> loginedUser;
    public MutableLiveData<Examinee> selectedExamniee;


    public MainViewModel() {
        this.loginedUser = new MutableLiveData<>();
        this.selectedExamniee = new MutableLiveData<>();
    }

}
