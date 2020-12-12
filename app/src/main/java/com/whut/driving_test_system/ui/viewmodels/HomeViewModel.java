package com.whut.driving_test_system.ui.viewmodels;

import android.view.View;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<Examinee> choisedExaminee;
    public MutableLiveData<Boolean> isCn;

    public HomeViewModel() {
        this.choisedExaminee = new MutableLiveData<>();
        this.isCn = new MutableLiveData<>(true);
    }


    public void onStartExam(View v) {
        NavController controller = Navigation.findNavController(v);
        controller.navigate(R.id.action_homeFragment_to_examFragment);
    }

    public void onChangeLanguage(View v) {
        isCn.setValue(!isCn.getValue());
    }
}
