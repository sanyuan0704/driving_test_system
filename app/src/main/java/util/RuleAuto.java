package util;

import androidx.lifecycle.LifecycleOwner;

import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.repository.RuleRepository;


abstract class RuleAuto {
    public Rule my_Rule;//存储规则
    public RuleRepository my_RuleRepository;//对
    public LifecycleOwner lifecycleOwner;
    public void autoExam(){};
}
