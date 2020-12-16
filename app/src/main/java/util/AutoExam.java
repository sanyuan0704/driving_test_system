package util;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.ExamnieeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;

import java.util.List;



public class AutoExam {
    private R1 r1;
    private R2 r2;
    private R3 r3;
    private R4 r4;
    private R5 r5;
    private R6 r6;
    private R7 r7;
    AutoExam(){
        //r1=new
    }
}


//抽象自动评分类
abstract class RuleAuto {
    public String my_rule_id;
    public Rule my_Rule;//存储规则
    public Examinee my_Examinee;//考试考生
    public RuleRepository my_RuleRepository;//对规则数据库操作
    public LifecycleOwner lifecycleOwner;//操作数据库必备
    public ExamnieeRespository my_ExamnieeRespository;//对考生数据库操作

    public Rule autoExam(String firstThresholdValue) {
        if (Integer.parseInt(firstThresholdValue) <= Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
            my_ExamnieeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        return my_Rule;
    }//自动判分函数

    public void setRule() {
        my_RuleRepository.getAllLiveRules().observe(lifecycleOwner, new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                for (int i = 0; i < rules.size(); i++) {
                    if (rules.get(i).isAuto == true && my_rule_id.equals(rules.get(i).ruleId)) {
                        my_Rule = new Rule(rules.get(i));
                    }
                }
            }
        });
    }
}

class R1 extends RuleAuto {
    R1(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "R1";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }
}

class R2 extends RuleAuto {
    R2(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R2";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }
}

class R3 extends RuleAuto {
    R3(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R3";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }

    public Rule autoExam(String firstThresholdValue) {
        if (Integer.parseInt(firstThresholdValue) > Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
            my_ExamnieeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        return my_Rule;
    }//自动判分函数
}

class R5 extends RuleAuto {
    R5(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R5";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }
}

class R6 extends RuleAuto {
    R6(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R6";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }

    public Rule autoExam(String firstThresholdValue) {
        if (Integer.parseInt(firstThresholdValue) < Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
        }
    }//自动判分函数
}

class R7 extends RuleAuto {
    R7(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R7";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }

    public Rule autoExam(String firstThresholdValue, String secondThresholdValue) {
        if (!firstThresholdValue.equals("普通路段") && Integer.parseInt(secondThresholdValue) < Integer.parseInt(my_Rule.secondThresholdValue)) {
            //写入犯错
        }
    }
}

class R4 extends RuleAuto {
    R4(LifecycleOwner lifecycleOwner, Context context) {
        my_rule_id = "R4";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamnieeRespository = new ExamnieeRespository(context);
    }

    public Rule autoExam(String firstThresholdValue, String secondThresholdValue) {
        if (Integer.parseInt(firstThresholdValue) >= Integer.parseInt(my_Rule.firstThresholdValue) && Integer.parseInt(secondThresholdValue) < Integer.parseInt(my_Rule.secondThresholdValue)) {
            //写入犯错
        }
    }
}
