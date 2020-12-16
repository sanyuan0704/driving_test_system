package com.whut.driving_test_system.models.eneities;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

public class RuleWithExaminees {
    @Embedded
    public Rule rule;
    @Relation(
            parentColumn = "ruleId",
            entityColumn = "examNumber",
            associateBy = @Junction(ExamineeRuleRef.class)
    )
    public List<Examinee> examinees;
}
