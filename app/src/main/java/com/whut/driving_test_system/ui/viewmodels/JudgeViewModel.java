package com.whut.driving_test_system.ui.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.ExamineeRespository;

import java.util.List;


public class JudgeViewModel extends ViewModel {
    public MutableLiveData<Examinee> examinee;//考生
    public ExamineeRespository my_ExamineeRespository;//对考生数据库操作


    public JudgeViewModel() {
        this.examinee = new MutableLiveData<>();
    }






}
