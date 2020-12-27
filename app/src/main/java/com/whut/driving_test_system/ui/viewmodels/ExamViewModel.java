package com.whut.driving_test_system.ui.viewmodels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;

import com.whut.driving_test_system.R;
import com.whut.driving_test_system.databinding.FragmentExamBinding;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.ExamineeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import util.AutoExam;


public class ExamViewModel extends ViewModel {
    public MutableLiveData<List<Rule>> my_Rule;//所有的规则 拿下来
    public RuleRepository my_RuleRepository;//对规则数据库操作
    public ExamineeRespository my_ExamineeRespository;//对考生数据库操作
    private AutoExam autoExam;//自动评分类
    public MutableLiveData<Examinee> examinee;//考生
    public MutableLiveData<List<Rule>> validRules;//扣分列表
    public MutableLiveData<String> roadChose;//道路选项
    public MutableLiveData<List<String>> orderList;//已考指令列表
    public MutableLiveData<Boolean> isStart; // 考试是否开始

    public ExamViewModel() {
        this.examinee = new MutableLiveData<>();
        this.my_Rule = new MutableLiveData<List<Rule>>();
        this.validRules = new MutableLiveData<List<Rule>>();
        this.roadChose = new MutableLiveData<String>();
        this.orderList = new MutableLiveData<List<String>>();
        this.isStart = new MutableLiveData<>(false);
    }

    /**
     * 将考生犯错信息插入表
     */
    public void setAllRules(Context context) {
        my_ExamineeRespository = new ExamineeRespository(context);
        List<Rule> a_rulelist = validRules.getValue();
        for (int i = 0; i < a_rulelist.size(); i++) {
            my_ExamineeRespository.insertExamnieeRuleRef(examinee.getValue(), a_rulelist.get(i));
        }
    }

    /**
     * 获取所有规则列表
     */
    public void getAllRules(LifecycleOwner lifecycleOwner, Context context) {
        my_RuleRepository = new RuleRepository(context);
        my_RuleRepository.getAllLiveRules().observe(lifecycleOwner, new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                my_Rule.setValue(rules);
            }
        });
    }

    /**
     * 自动评分函数
     */
    public void autoExamFunction(Context context, List<String> my_list, FragmentExamBinding binding) {
        autoExam = new AutoExam(context, examinee.getValue(), my_Rule.getValue());
        Rule a_Rule = new Rule();
        a_Rule = autoExam.R1_autoExamJudge(my_list.get(0));//与道路右边线间距(CM):
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
        a_Rule = autoExam.R2_autoExamJudge(my_list.get(1));//与道路中心线间距(CM):
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
        a_Rule = autoExam.R3_autoExamJudge(my_list.get(2));//车速(KM/h)
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
        a_Rule = autoExam.R5_autoExamJudge(my_list.get(4));//发动机转速(转/s):
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
        a_Rule = autoExam.R7_autoExamJudge(roadChose.getValue(), my_list.get(5));//路口减速
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
    }

    /**
     * 自动评分 判断里程
     */

    public void autoMileageFunction(Context context, String mileage, FragmentExamBinding binding) {
        autoExam = new AutoExam(context, examinee.getValue(), my_Rule.getValue());
        Rule a_Rule = new Rule();
        a_Rule = autoExam.R6_autoExamJudge(mileage);//形式里程
        if (a_Rule.ruleId != null) {
            List<Rule> a_rulelist = validRules.getValue();
            a_rulelist.add(a_Rule);
            validRules.setValue(a_rulelist);
        }
    }


    /**
     * 弹窗处理函数
     */
    public void showAlertDialogAndSelectRules(View view, String nickname) {
        if (my_Rule.getValue() == null || my_Rule.getValue().size() == 0) return;
        // 拿到相应规则
        final List<Rule> ruleList = my_Rule.getValue();
        final List<Rule> currentRules = new ArrayList<>();
        List<String> tips = new ArrayList<>();
        for (Rule rule : ruleList) {
            if (rule.nickname.equals(nickname)) {
                currentRules.add(rule);
                tips.add(rule.content);
            }
        }

        final boolean[] isSelect = new boolean[tips.size()];

        // 开始弹窗
        new AlertDialog.Builder(view.getContext())
                .setTitle("选择要扣分的项目")
                .setMultiChoiceItems(tips.toArray(new String[tips.size()]), isSelect, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        isSelect[i] = b;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int index = 0; index < isSelect.length; index++) {
                            if (isSelect[index]) {
                                //validRules.observe();
                                List<Rule> a_rulelist = validRules.getValue();
                                a_rulelist.add(ruleList.get(index));
                                validRules.setValue(a_rulelist);
                            }
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 清空选中数组
                        for (int index = 0; index < isSelect.length; index++) {
                            isSelect[index] = false;
                        }
                    }
                })
                .create()
                .show();
    }

    /**
     * 起步按钮函数
     *
     * @param v
     */
    public void onStartExam(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.start);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "起步");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("起步");
        orderList.setValue(a_rulelist);
        isStart.setValue(true);
    }


    /**
     * 左转按钮函数
     *
     * @param v
     */
    public void onTurnLeft(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_left);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "拐弯");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("左拐");
        orderList.setValue(a_rulelist);
    }

    /**
     * 右转按钮函数
     *
     * @param v
     */
    public void onTurnRight(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_right);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "拐弯");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("右拐");
        orderList.setValue(a_rulelist);
    }

    /**
     * 超车按钮函数
     *
     * @param v
     */
    public void onOvertaking(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.overtaking);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "超车");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("超车");
        orderList.setValue(a_rulelist);
    }

    /**
     * 掉头按钮函数
     *
     * @param v
     */
    public void onTurnAround(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.turn_around);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "掉头");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("掉头");
        orderList.setValue(a_rulelist);
    }

    /**
     * 变更车道按钮函数
     *
     * @param v
     */
    public void onChangeLane(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.change_lane);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "变更车道");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("变更车道");
        orderList.setValue(a_rulelist);
    }

    /**
     * 靠边停车按钮函数
     *
     * @param v
     */
    public void onStop(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.stop);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "靠边停车");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("靠边停车");
        orderList.setValue(a_rulelist);
    }

    /**
     * 重新起步按钮函数
     *
     * @param v
     */
    public void onRestart(View v) {
        MediaPlayer player = MediaPlayer.create(v.getContext(), R.raw.restart);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.start();
        showAlertDialogAndSelectRules(v, "起步");
        List<String> a_rulelist = orderList.getValue();
        a_rulelist.add("重新起步");
        orderList.setValue(a_rulelist);
    }

}
