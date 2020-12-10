package com.whut.driving_test_system.models.eneities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rule {
    @PrimaryKey
    public String ruleId;
    public String content;
    public String nickname;
    public String value;
    public String isAuto;
    public String isSelected;
    public String firstThresholdKey;   // 第一阈值名称
    public String firstThresholdValue; // 第一阈值
    public String secondThresholdKey;  // 第二阈值名称
    public String secondThresholdValue;// 第二阈值
}
