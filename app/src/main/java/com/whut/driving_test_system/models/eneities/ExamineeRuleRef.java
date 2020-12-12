package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"examNumber", "ruleId"})
public class ExamineeRuleRef {
    @NonNull
    public String examNumber;
    @NonNull
    public String ruleId;
}
