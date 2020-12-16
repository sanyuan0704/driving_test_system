package com.whut.driving_test_system.ui.viewmodels;

import android.os.Bundle;
import android.view.View;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.UserWithExaminees;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

public class HomeViewModel extends ViewModel {
    public LiveData<UserWithExaminees> userWithExaminees;
    public MutableLiveData<Examinee> choisedExaminee;
    public MutableLiveData<Boolean> isCn;
    public MutableLiveData<Integer> waitNumber;
    public MutableLiveData<Integer> passedNumber;
    public MutableLiveData<Integer> failedNumber;
    public MutableLiveData<Integer> allNumber;

    public HomeViewModel() {
        this.userWithExaminees = new MutableLiveData<>();
        this.choisedExaminee = new MutableLiveData<>(new Examinee());
        this.isCn = new MutableLiveData<>(true);
        this.waitNumber = new MutableLiveData<>(1);
        this.passedNumber = new MutableLiveData<>(0);
        this.failedNumber = new MutableLiveData<>(0);
        this.allNumber = new MutableLiveData<>(0);
    }

    public void onStartExam(View v) {
        Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_examFragment);
    }

    public void onChangeLanguage(View v) {
        isCn.setValue(!isCn.getValue());
    }
}
