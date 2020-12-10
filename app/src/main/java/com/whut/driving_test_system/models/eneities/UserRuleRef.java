package com.whut.driving_test_system.models.eneities;

import androidx.room.Entity;

@Entity(primaryKeys = {"ruleId", "userId"})
public class UserRuleRef {
    public String ruleId;
    public String userId;
}
