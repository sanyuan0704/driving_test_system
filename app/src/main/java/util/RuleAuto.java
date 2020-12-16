package util;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.RuleRepository;


//抽象自动评分类
abstract class RuleAuto {
    public Rule my_Rule;//存储规则
    public RuleRepository my_RuleRepository;//对规则数据库操作
    public LifecycleOwner lifecycleOwner;//操作数据库必备
    public void autoExam(){};//自动判分函数
}

class R1 extends RuleAuto{
    R1 (LifecycleOwner lifecycleOwner, Context context){
        this.lifecycleOwner=lifecycleOwner;
        my_RuleRepository = new RuleRepository(context);
        //my_RuleRepository.
    }
}



