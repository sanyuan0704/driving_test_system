package com.whut.driving_test_system.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;

public class RuleViewModel extends AndroidViewModel {
    private RuleRepository ruleRepository;

    public RuleViewModel(@NonNull Application application) {
        super(application);
        ruleRepository = new RuleRepository(application);
    }

    public LiveData<List<Rule>> getAllRuleLive() {
        return ruleRepository.getAllLiveRules();
    }

    public void insertRule() {
        ArrayList<Rule> rules = new ArrayList<>();
        Rule r1 = new Rule("1", "车辆行驶中骑轧车道边沿实线", "压边线", "100", true, true, "考试车辆距离道路右侧边缘线值", "0CM", null, null);
        Rule r2 = new Rule("2", "车辆行驶中骑轧车道中心实线", "压中心线", "100", true, true, "考试车辆距离道路中心(双黄线、单黄线、白实线)实线值", "0CM", null, null);
        Rule r3 = new Rule("3", "车速超过限速规定", "超速", "10", true, true, "考车辆行驶速度状态", "80KM/H", null, null);
        Rule r4 = new Rule("4", "开转向灯小于3秒即转向", "提前转向", "10", true, true, "车辆行驶状态", "起步前，超车前，变更到快车道，变更到慢车道前，左转前，右转前，调头前，靠边停车前", "灯光开关时间", "3S");


        ruleRepository.insertRule();
    }

    public void updateRule(Rule... rules) {
        ruleRepository.updateRule(rules);
    }
}
