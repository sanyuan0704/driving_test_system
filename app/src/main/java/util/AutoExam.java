package util;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.ExamineeRespository;
import com.whut.driving_test_system.models.repository.RuleRepository;

import java.util.List;



public class AutoExam {
    public R1 r1;
    public R2 r2;
    public R3 r3;
    public R4 r4;
    public R5 r5;
    public R6 r6;
    public R7 r7;
    public AutoExam(LifecycleOwner lifecycleOwner, Context context, Examinee my_Examinee){
        r1=new R1(lifecycleOwner,context,my_Examinee);
        r2=new R2(lifecycleOwner,context,my_Examinee);
        r3=new R3(lifecycleOwner,context,my_Examinee);
        r4=new R4(lifecycleOwner,context,my_Examinee);
        r5=new R5(lifecycleOwner,context,my_Examinee);
        r6=new R6(lifecycleOwner,context,my_Examinee);
        r7=new R7(lifecycleOwner,context,my_Examinee);
    }
    public Rule R1_autoExamJudge(String firstThresholdValue){
        return  r1.autoExamJudge(firstThresholdValue);
    }
    public Rule R2_autoExamJudge(String firstThresholdValue){
        return  r2.autoExamJudge(firstThresholdValue);
    }
    public Rule R3_autoExamJudge(String firstThresholdValue){
        return  r3.autoExamJudge(firstThresholdValue);
    }
    public Rule R4_autoExamJudge(String firstThresholdValue, String secondThresholdValue){
        return  r4.autoExamJudge(firstThresholdValue,secondThresholdValue);
    }
    public Rule R5_autoExamJudge(String firstThresholdValue){
        return  r5.autoExamJudge(firstThresholdValue);
    }
    public Rule R6_autoExamJudge(String firstThresholdValue){
        return  r6.autoExamJudge(firstThresholdValue);
    }
    public Rule R7_autoExamJudge(String firstThresholdValue, String secondThresholdValue){
        return  r7.autoExamJudge(firstThresholdValue,secondThresholdValue);
    }

}


//抽象自动评分类
class RuleAuto {
    public String my_rule_id;
    public Rule my_Rule;//存储规则
    public Examinee my_Examinee;//考试考生
    public RuleRepository my_RuleRepository;//对规则数据库操作
    public LifecycleOwner lifecycleOwner;//操作数据库必备
    public ExamineeRespository my_ExamineeRespository;//对考生数据库操作
    public Rule autoExamJudge(String firstThresholdValue) {
        if (my_Rule.isSelected==true&&Integer.parseInt(firstThresholdValue) <= Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
            my_ExamineeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        Rule rule= new Rule();
        return rule;
    }//自动判分函数

    public void setRule() {
        my_RuleRepository.getAllLiveRules().observe(lifecycleOwner, new Observer<List<Rule>>() {
            @Override
            public void onChanged(List<Rule> rules) {
                for (int i = 0; i < rules.size(); i++) {
                    if (rules.get(i).isAuto == true && my_rule_id.equals(rules.get(i).ruleId)) {
                        my_Rule = rules.get(i);
                    }
                }
            }
        });
    }
}

class R1 extends RuleAuto {
    R1(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r1";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }
}

class R2 extends RuleAuto {
    R2(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r2";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }
}

class R3 extends RuleAuto {
    R3(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r3";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }

    public Rule autoExamJudge(String firstThresholdValue) {
        if (my_Rule.isSelected==true&&Integer.parseInt(firstThresholdValue) > Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
            my_ExamineeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        Rule rule= new Rule();
        return rule;
    }//自动判分函数
}

class R5 extends RuleAuto {
    R5(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r5";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }
}

class R6 extends RuleAuto {
    R6(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r6";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }

    public Rule autoExamJudge(String firstThresholdValue) {
        if (my_Rule.isSelected==true&&Integer.parseInt(firstThresholdValue) < Integer.parseInt(my_Rule.firstThresholdValue)) {
            //写入犯错
            my_ExamineeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        Rule rule= new Rule();
        return rule;
    }//自动判分函数
}

class R7 extends RuleAuto {
    R7(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r7";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }

    public Rule autoExamJudge(String firstThresholdValue, String secondThresholdValue) {
        if (my_Rule.isSelected==true&&!firstThresholdValue.equals("普通路段") && Integer.parseInt(secondThresholdValue) < Integer.parseInt(my_Rule.secondThresholdValue)) {
            //写入犯错
            my_ExamineeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        Rule rule= new Rule();
        return rule;
    }
}

class R4 extends RuleAuto {
    R4(LifecycleOwner lifecycleOwner, Context context,Examinee my_Examinee) {
        my_rule_id = "r4";
        this.lifecycleOwner = lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        my_Rule =new Rule();
        setRule();
        this.my_Examinee = my_Examinee;
        my_ExamineeRespository = new ExamineeRespository(context);
    }

    public Rule autoExamJudge(String firstThresholdValue, String secondThresholdValue) {
        if (my_Rule.isSelected==true&&Integer.parseInt(firstThresholdValue) >= Integer.parseInt(my_Rule.firstThresholdValue) && Integer.parseInt(secondThresholdValue) < Integer.parseInt(my_Rule.secondThresholdValue)) {
            //写入犯错
            my_ExamineeRespository.insertExamnieeRuleRef(my_Examinee,my_Rule);
            return my_Rule;
        }
        Rule rule= new Rule();
        return rule;
    }
}
