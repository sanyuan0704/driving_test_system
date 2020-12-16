package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
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

    public Rule(Rule rule) {
        this.ruleId = rule.ruleId;
        this.content = rule.content;
        this.nickname = rule.nickname;
        this.value = rule.value;
        this.isAuto = rule.isAuto;
        this.isSelected = rule.isSelected;
        this.firstThresholdKey = rule.firstThresholdKey;
        this.firstThresholdValue = rule.firstThresholdValue;
        this.secondThresholdKey = rule.secondThresholdKey;
        this.secondThresholdValue = rule.secondThresholdValue;
    }
}
