package com.whut.driving_test_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.whut.driving_test_system.databinding.ActivityMainBinding;
import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.ExamnieeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new MainViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        binding.settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(findViewById(R.id.fragment)).navigate(R.id.settingsFragment);
            }
        });

        initDB();
    }


    private void initDB() {
        UserRepository userRepository = new UserRepository(this);
        ExamnieeRespository examnieeRespository = new ExamnieeRespository(this);
        RuleRepository ruleRepository = new RuleRepository(this);

        userRepository.deleteAllUsers();
        examnieeRespository.deleteAllExamniee();
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
                new Rule("r1", "车辆行驶中骑轧车道边沿实线", "压边线", "100", true, true, "考试车辆距离道路右侧边缘线值", "0CM", null, null),
                new Rule("r2", "车辆行驶中骑轧车道中心实线", "压中心线", "100", true, true, "考试车辆距离道路中心(双黄线、单黄线、白实线)实线值", "0CM", null, null),
                new Rule("r3", "车速超过限速规定", "超速", "10", true, true, "考车辆行驶速度状态", "80KM/H", null, null),
                new Rule("r4", "开转向灯小于3秒即转向", "提前转向", "10", true, true, "车辆行驶状态", "起步前，超车前，变更到快车道，变更到慢车道前，左转前，右转前，调头前，靠边停车前", "灯光开关时间", "3S")
        );

    }
}
