package com.whut.driving_test_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.whut.driving_test_system.ui.fragments.SettingsFragment;
import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    public MainViewModel mainViewModel;
    private ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new MainViewModel();
        settingButton = findViewById(R.id.setting_button);

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(findViewById(R.id.fragment)).navigate(R.id.settingsFragment);
            }
        });
    }
}
