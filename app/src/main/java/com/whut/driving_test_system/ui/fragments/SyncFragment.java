package com.whut.driving_test_system.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.repository.ExamineeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;
import com.whut.driving_test_system.models.repository.UserRepository;

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
                initDB();
            }
        });
        return view;

    }

    private void initDB() {
        UserRepository userRepository = new UserRepository(getContext());
        ExamineeRespository examnieeRespository = new ExamineeRespository(getContext());
        RuleRepository ruleRepository = new RuleRepository(getContext());

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
                new Examinee("202012120001", "345678200005192349", "熊大", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "http://104.131.131.82:8080/ipfs/QmW2jF7RGanMHjC9CQkNVMWsmjAT9MmN9PWjMpTbZL3RpU", "e1"),
                new Examinee("202012120004", "345678200005192349", "蹦蹦", "森林驾校", 0, "C1", Examinee.ExamStatus.WAIT.ordinal(), 0, "http://104.131.131.82:8080/ipfs/QmbWeWVS36eFjQUyhSnXLpgiYoDpTMwafL9GNm31n5PSpK", "e1"),
                new Examinee("202012120002", "345678200005194560", "熊二", "森林驾校", 0, "C1", Examinee.ExamStatus.PASSED.ordinal(), 100, "http://104.131.131.82:8080/ipfs/QmXzy99FkwJY1CQNgLif8QNvoHe4VkkyuSkDqN3PBy8Jgv", "e1"),
                new Examinee("202012120003", "345678198801010002", "光头强", "森林驾校", 1, "C1", Examinee.ExamStatus.FAILED.ordinal(), 80, "http://104.131.131.82:8080/ipfs/QmNVCoWkszQLaWShJB5VCw6bapkDJCir2GpkXBvFz1HnyE", "e1")
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
