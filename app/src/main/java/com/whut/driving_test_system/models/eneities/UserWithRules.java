package com.whut.driving_test_system.models.eneities;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

public class UserWithRules {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "ruleId",
            associateBy = @Junction(UserRuleRef.class)
    )
    public List<Rule> rules;
}
