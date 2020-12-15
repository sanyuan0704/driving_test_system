package com.whut.driving_test_system.ui.viewmodels;

import android.content.Context;
import android.widget.Toast;

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
    private List name_list;
    //public Context context;

    public ExamerViewModel() {
        this.my_user = new MutableLiveData<>();
        this.my_list = new MutableLiveData<List<String>>();
    }

    public void setContext(Context context) {
        //this.context=context;
        name_list = new ArrayList();
        my_UserRepository = new UserRepository(context);
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void getAllUsers() {
        my_UserRepository.getAllUsers().observe(lifecycleOwner, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                List<String> a_list = new ArrayList<String>();
                name_list.clear();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).usertype == User.UserTypes.EXAMINER.ordinal()) {
                        a_list.add(users.get(i).userId);
                        name_list.add(users.get(i).username);
                    }
                }
                my_list.setValue(a_list);
            }
        });
    }

    public void findAUsers(String userId) {
        my_UserRepository.getUserById(userId).observe(lifecycleOwner, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    my_user.setValue(user);
                } else {
                    User a_user = new User();
                    my_user.setValue(a_user);
                }
            }
        });
    }

    public void deleteAUsers() {
        my_UserRepository.deleteUsers(my_user.getValue());
        getAllUsers();
    }

    public void changeAUsers(String name, String username, String password) {
        my_user.getValue().name = name;
        my_user.getValue().username = username;
        my_user.getValue().password = password;
        my_UserRepository.updateUsers(my_user.getValue());
        getAllUsers();
    }

    public Boolean addAUsers(String id, String name, String username, String password) {
        //不能重复ID
        if (!my_list.getValue().contains(id) && !id.equals("") && !name_list.contains(username)
                && !name.equals("")&& !username.equals("")&& !password.equals("")) {
            my_user.getValue().userId = id;
            my_user.getValue().name = name;
            my_user.getValue().username = username;
            my_user.getValue().password = password;
            my_UserRepository.insertUsers(my_user.getValue());
            getAllUsers();
            return true;
        } else {
            getAllUsers();
            return false;
        }
    }


}
