package com.whut.driving_test_system;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.whut.driving_test_system.databinding.ActivityMainBinding;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mainViewModel.loginedUser.getValue() == null) {
                    Toast.makeText(MainActivity.this, "请登录后再操作", Toast.LENGTH_SHORT).show();
                } else if (mainViewModel.isExaming.getValue()) {
                    Toast.makeText(MainActivity.this, "正在考试中，请勿离开考试界面", Toast.LENGTH_SHORT).show();
                } else {
                    switch (item.getItemId()) {
                        case R.id.welcomeFragment:
                            item.setChecked(true);
                            Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.welcomeFragment);
                            break;

                        case R.id.homeFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.EXAMINER.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是考官，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else {
                                item.setChecked(true);
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.homeFragment);
                            }
                            break;

                        case R.id.examFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.EXAMINER.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是考官，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else if (!mainViewModel.isExaming.getValue()) {
                                Toast.makeText(MainActivity.this, "没有正在进行中的考试", Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case R.id.syncFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.EXAMINER.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是考官，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else {
                                item.setChecked(true);
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.syncFragment);
                            }
                            break;

                        case R.id.settingsFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.ADMIN.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是管理员，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else {
                                item.setChecked(true);
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.settingsFragment);
                            }
                            break;
                    }
                }
                return false;
            }
        });

        mainViewModel.anchor.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.bottomNavigationView.getMenu().findItem(integer).setChecked(true);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // 返回键阻断
    }


}
