package com.whut.driving_test_system.models.eneities;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

public class ExamineeWithRules {
    @Embedded
    public Examinee examinee;
    @Relation(
            parentColumn = "examNumber",
            entityColumn = "ruleId",
            associateBy = @Junction(ExamineeRuleRef.class)
    )
    public List<Rule> rules;
}
