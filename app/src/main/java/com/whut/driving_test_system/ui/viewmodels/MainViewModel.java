package com.whut.driving_test_system.ui.viewmodels;

import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.ui.fragments.ExamFragment;
import com.whut.driving_test_system.ui.fragments.HomeFragment;
import com.whut.driving_test_system.ui.fragments.SettingsFragment;
import com.whut.driving_test_system.ui.fragments.SyncFragment;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<User> loginedUser;
    public MutableLiveData<Map<Class, BarStatus>> fragmentStatus;
    public MutableLiveData<Map<Class, String>> fragmentTitle;

    public static enum BarStatus {ACTIVE, NOACTIVE, GONE}

    public MainViewModel() {
        this.loginedUser = new MutableLiveData<>();
        this.fragmentStatus = new MutableLiveData<>();
        HashMap<Class, BarStatus> statusMap = new HashMap<>();
        statusMap.put(HomeFragment.class, BarStatus.NOACTIVE);
        statusMap.put(ExamFragment.class, BarStatus.NOACTIVE);
        statusMap.put(SyncFragment.class, BarStatus.NOACTIVE);
        statusMap.put(SettingsFragment.class, BarStatus.NOACTIVE);
        this.fragmentStatus.setValue(statusMap);

        this.fragmentTitle = new MutableLiveData<>();
        HashMap<Class, String> titleMap = new HashMap<>();
        titleMap.put(HomeFragment.class, "");
        titleMap.put(ExamFragment.class, "");
        titleMap.put(SyncFragment.class, "");
        titleMap.put(SettingsFragment.class, "");
        this.fragmentTitle.setValue(titleMap);
    }

    public void setBarStatus(Class cls, BarStatus status) {
        Map<Class, BarStatus> map = fragmentStatus.getValue();
        map.put(cls, status);
        fragmentStatus.setValue(map);
    }

    public void setBarTitle(Class cls, String title) {
        Map<Class, String> map = fragmentTitle.getValue();
        map.put(cls,title);
        fragmentTitle.setValue(map);
    }
}
