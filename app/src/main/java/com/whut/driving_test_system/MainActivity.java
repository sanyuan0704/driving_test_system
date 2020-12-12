package com.whut.driving_test_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.ExamnieeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;
import com.whut.driving_test_system.models.repository.UserRepository;
import com.whut.driving_test_system.ui.viewmodels.LoginViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingButton = findViewById(R.id.setting_button);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(findViewById(R.id.fragment)).navigate(R.id.settingsFragment);
            }
        });
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


    }
}
