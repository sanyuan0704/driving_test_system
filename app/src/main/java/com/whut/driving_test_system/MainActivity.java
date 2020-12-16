package com.whut.driving_test_system;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.whut.driving_test_system.databinding.ActivityMainBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.ExamineeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;
import com.whut.driving_test_system.models.repository.UserRepository;
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
                            Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.welcomeFragment);
                            break;

                        case R.id.homeFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.EXAMINER.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是考官，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else {
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.homeFragment);
                                item.setChecked(true);
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
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.syncFragment);
                                item.setChecked(true);
                            }
                            break;

                        case R.id.settingsFragment:
                            if (mainViewModel.loginedUser.getValue().usertype != User.UserTypes.ADMIN.ordinal()) {
                                Toast.makeText(MainActivity.this, "您不是管理员，请重新登陆", Toast.LENGTH_SHORT).show();
                            } else {
                                Navigation.findNavController(MainActivity.this, R.id.fragment).navigate(R.id.settingsFragment);
                                item.setChecked(true);
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

        initDB();
    }


    private void initDB() {
        UserRepository userRepository = new UserRepository(this);
        ExamineeRespository examnieeRespository = new ExamineeRespository(this);
        RuleRepository ruleRepository = new RuleRepository(this);

        userRepository.deleteAllUsers();
        examnieeRespository.deleteAllExamniee();
        examnieeRespository.deleteAllExamineeRuleRef();
        ruleRepository.deleteAllRules();

        userRepository.insertUsers(
                new User("e1", "小橘子", "juzi", "123", User.UserTypes.EXAMINER.ordinal()),
                new User("e2", "白勇太", "baiyongtai", "123", User.UserTypes.EXAMINER.ordinal()),
                new User("a1", "一号管理员", "admin01", "123", User.UserTypes.ADMIN.ordinal())
        );

        examnieeRespository.insertExaminees(
                new Examinee("202012120001", "345678200005192349", "熊大", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "e1"),
                new Examinee("202012120004", "345678200005192349", "蹦蹦", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "e1"),
                new Examinee("202012120002", "345678200005194560", "熊二", "森林驾校", 0, "C1", Examinee.ExamStatus.PASSED.ordinal(), 100, "e1"),
                new Examinee("202012120003", "345678198801010002", "光头强", "森林驾校", 1, "C1", Examinee.ExamStatus.FAILED.ordinal(), 80, "e1")
        );

        ruleRepository.insertRule(
                new Rule("er1", "不能正确观察交通情况选择掉头时机", "掉头", "100", false, true, null, null, null, null),
                new Rule("er2", "掉头地点选择不当", "掉头", "100", false, true, null, null, null, null),
                new Rule("er3", "掉头时，妨碍正常形式的其他车辆和行人通过", "掉头", "10", false, true, null, null, null, null),
                new Rule("er4", "超车时，为回头观察被超越车辆动态", "超车", "100", false, true, null, null, null, null),
                new Rule("er5", "超车时未与被超越车辆保持安全距离", "超车", "100", false, true, null, null, null, null),
                new Rule("er6", "在没有中心线或同方向只有一条车道的道路上从右侧超车", "超车", "100", false, true, null, null, null, null),
                new Rule("er7", "超车时机选择不合理，影响其他车辆正常行驶", "超车", "100", false, true, null, null, null, null),
                new Rule("er8", "不按规定减速或停车观望", "拐弯", "100", false, true, null, null, null, null),
                new Rule("er9", "不主动避让优先通行的车辆、行人、非机动车", "拐弯", "100", false, true, null, null, null, null),
                new Rule("er10", "遇到路口交通堵塞时进入路口，将车辆停在路口内等待", "拐弯", "100", false, true, null, null, null, null),
                new Rule("er11", "制动气压不足起步", "起步", "100", false, true, null, null, null, null),
                new Rule("er12", "道路交通情况复杂时起步不能合理使用喇叭", "起步", "5", false, true, null, null, null, null),
                new Rule("er13", "启动发动机前，不检查调整驾驶座椅、后视镜、检查仪表", "起步", "5", false, true, null, null, null, null),
                new Rule("er14", "变更车道前，未通过内、外后视镜观察，并向变更车道方向回头观察后方道路交通情况", "变更车道", "100", false, true, null, null, null, null),
                new Rule("er15", "变更车道时，判断车辆安全距离不合理，妨碍其他车辆正常行驶", "变更车道", "100", false, true, null, null, null, null),
                new Rule("er16", "变更车道时，控制行驶速度不合理，妨碍其他车辆正常行驶", "变更车道", "100", false, true, null, null, null, null),
                new Rule("er17", "未能在规定的距离内停车", "靠边停车", "100", false, true, null, null, null, null),
                new Rule("er18", "停车后，打开车门前不回头观察左后方交通情况", "靠边停车", "100", false, true, null, null, null, null),
                new Rule("er19", "停车后，车身距离道路右侧边缘线或者人行道边缘超过50CM", "靠边停车", "100", false, true, null, null, null, null),
                new Rule("er20", "停车后，车身距离道路右侧边缘线或者人行道边缘超过30CM，未超过50CM", "靠边停车", "10", false, true, null, null, null, null),
                new Rule("er21", "停车后，未拉紧驻车制动器", "靠边停车", "10", false, true, null, null, null, null),

                new Rule("r1", "车辆行驶中骑轧车道边沿实线", "压边线", "100", true, true, "考试车辆距离道路右侧边缘线值", "0", null, null),
                new Rule("r2", "车辆行驶中骑轧车道中心实线", "压中心线", "100", true, true, "考试车辆距离道路中心(双黄线、单黄线、白实线)实线值", "0", null, null),
                new Rule("r3", "车速超过限速规定", "超速", "10", true, true, "考试车辆行驶速度状态", "40", null, null),
                new Rule("r5", "因操作不当发动机熄火一次", "熄火", "10", true, true, "考试车辆发动机转速状态", "0", null, null),
                new Rule("r6", "考试里程小于最小里程", "里程不足", "100", true, true, "考试车辆行驶里程", "3", null, null),
                new Rule("r7", "通过路口前未减速慢行", "通过路口不减速", "100", true, true, "考试车辆形式或停放区域", "普通路段", "考试车辆制动踏板状态", "30"),
                new Rule("r4", "开转向灯小于3秒即转向", "提前转向", "10", true, true, "考试车辆方向盘转角", "30", "灯光开关时间", "3")
        );

    }
}
