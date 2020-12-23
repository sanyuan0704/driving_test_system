package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index("examNumber"),@Index("ruleId")})
public class ExamineeRuleRef {
    @PrimaryKey(autoGenerate = true)
    public int refId;
    @NonNull
    public String examNumber;
    @NonNull
    public String ruleId;
}
