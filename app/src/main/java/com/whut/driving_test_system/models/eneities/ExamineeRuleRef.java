package com.whut.driving_test_system.models.eneities;

import androidx.room.Entity;

@Entity(primaryKeys = {"examNumber", "ruleId"})
public class ExamineeRuleRef {
    public String examNumber;
    public String ruleId;
}
