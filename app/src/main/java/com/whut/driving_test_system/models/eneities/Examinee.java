package com.whut.driving_test_system.models.eneities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Examinee {

    @PrimaryKey
    public String examNumber;
    public String idNumber;
    public String name;
    public String school;
    public int examCount;
    public String examType;
    public String examStatus;
    public int grade;
    // TODO: image
    public String examinerID;

    public Examinee(String examNumber, String idNumber, String name, String school, int examCount, String examType, String examStatus, int grade, String examinerID) {
        this.examNumber = examNumber;
        this.idNumber = idNumber;
        this.name = name;
        this.school = school;
        this.examCount = examCount;
        this.examType = examType;
        this.examStatus = examStatus;
        this.grade = grade;
        this.examinerID = examinerID;
    }
}
