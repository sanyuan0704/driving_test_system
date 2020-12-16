package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Rule {
    @PrimaryKey
    @NonNull
    public String ruleId;
    public String content;
    public String nickname;
    public String value;
    public Boolean isAuto;
    public Boolean isSelected;
    public String firstThresholdKey;   // 第一阈值名称
    public String firstThresholdValue; // 第一阈值
    public String secondThresholdKey;  // 第二阈值名称
    public String secondThresholdValue;// 第二阈值

    @Ignore
    public Rule() {
    }

    public Rule(@NonNull String ruleId, String content, String nickname, String value, Boolean isAuto, Boolean isSelected, String firstThresholdKey, String firstThresholdValue, String secondThresholdKey, String secondThresholdValue) {
        this.ruleId = ruleId;
        this.content = content;
        this.nickname = nickname;
        this.value = value;
        this.isAuto = isAuto;
        this.isSelected = isSelected;
        this.firstThresholdKey = firstThresholdKey;
        this.firstThresholdValue = firstThresholdValue;
        this.secondThresholdKey = secondThresholdKey;
        this.secondThresholdValue = secondThresholdValue;
    }
}
