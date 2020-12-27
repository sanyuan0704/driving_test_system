package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.repository.ExamineeRespository;

import androidx.fragment.app.Fragment;

/**
 * 实现功能：
 * 1. 上传 / 导入考试信息（包括考官，考生等）
 * 2. 上传 / 导出考试成绩
 */
public class SyncFragment extends Fragment {
    private Button upLoadButton, downLoadButton;

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
                uploadExamniees();
            }
        });
        downLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "考生信息下载成功", Toast.LENGTH_SHORT).show();
                downloadExamniees();
            }
        });
        return view;

    }

    private void uploadExamniees() {
        ExamineeRespository examnieeRespository = new ExamineeRespository(getContext());
        examnieeRespository.deleteAllExamniee();
        examnieeRespository.deleteAllExamineeRuleRef();
    }

    private void downloadExamniees() {
        ExamineeRespository examnieeRespository = new ExamineeRespository(getContext());
        examnieeRespository.deleteAllExamniee();
        examnieeRespository.deleteAllExamineeRuleRef();
        examnieeRespository.insertExaminees(
                new Examinee("202012120001", "345678200005192349", "熊大", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "https://wuzhiying-test.oss-cn-beijing.aliyuncs.com/xd.png", "e1"),
                new Examinee("202012120004", "345678200005192349", "蹦蹦", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "https://wuzhiying-test.oss-cn-beijing.aliyuncs.com/bb.png", "e1"),
                new Examinee("202012120002", "345678200005194560", "熊二", "森林驾校", 0, "C1", Examinee.ExamStatus.PASSED.ordinal(), 100, "https://wuzhiying-test.oss-cn-beijing.aliyuncs.com/xe.png", "e1"),
                new Examinee("202012120003", "345678198801010002", "光头强", "森林驾校", 1, "C1", Examinee.ExamStatus.FAILED.ordinal(), 80, "https://wuzhiying-test.oss-cn-beijing.aliyuncs.com/gtq.png", "e1")
        );
    }
}
