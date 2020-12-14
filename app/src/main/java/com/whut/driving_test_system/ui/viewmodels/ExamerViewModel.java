package com.whut.driving_test_system.ui.viewmodels;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ExamerViewModel extends ViewModel {
    public MutableLiveData<User> my_user;//选择一个考官后显示的user
    public MutableLiveData<List<String>> my_list;//下拉列表
    public UserRepository my_UserRepository;
    public LifecycleOwner lifecycleOwner;
    //public Context context;

    public ExamerViewModel() {
        this.my_user = new MutableLiveData<>();
        this.my_list = new MutableLiveData<List<String>>();
    }

    public void setContext(Context context){
        //this.context=context;
        my_UserRepository=new UserRepository(context);
    }
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner){
        this.lifecycleOwner=lifecycleOwner;
    }

    public void getAllUsers(){
        my_UserRepository.getAllUsers().observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                List<String> a_list=new ArrayList<String>();
                for (int i = 0;i<users.size();i++)
                {
                    if(users.get(i).usertype==User.UserTypes.EXAMINER.ordinal()) {
                        a_list.add(users.get(i).userId);}
                }
                my_list.setValue(a_list);
            }
        });
    }

    public void findAUsers(String userId){
        my_UserRepository.getUserById(userId).observe(lifecycleOwner, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                my_user.setValue(user);
            }
        });
    }





}
