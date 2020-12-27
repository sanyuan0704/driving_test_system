package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.whut.driving_test_system.R;

import androidx.fragment.app.Fragment;

/**
 * 实现功能：
 * 1. 上传 / 导入考试信息（包括考官，考生等）
 * 2. 上传 / 导出考试成绩
 */
public class SyncFragment extends Fragment {
    private Button upLoadButton, downLoadButton;
    private View view;
    public SyncFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        upLoadButton = view.findViewById(R.id.upload_button);
        downLoadButton = view.findViewById(R.id.download_button);
        upLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "考试成绩上传成功", Toast.LENGTH_SHORT).show();
            }
        });
        downLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "考生信息下载成功", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
