package com.whut.driving_test_system.models.eneities;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

public class RuleWithUsers {
    @Embedded
    public Rule rule;
    @Relation(
            parentColumn = "ruleId",
            entityColumn = "userId",
            associateBy = @Junction(UserRuleRef.class)
    )
    public List<User> users;
}
