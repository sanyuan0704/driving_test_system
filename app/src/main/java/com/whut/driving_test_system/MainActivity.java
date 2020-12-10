package com.whut.driving_test_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.whut.driving_test_system.ui.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    public MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new MainViewModel();
    }
}
