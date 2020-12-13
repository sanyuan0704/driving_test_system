package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Examinee {

    @PrimaryKey
    @NonNull
    public String examNumber;
    public String idNumber;
    public String name;
    public String school;
    public int examCount;
    public String examType;
    public int examStatus;
    public int grade;
    // TODO: image
    public String examinerID;

    public static enum ExamStatus {WAIT, PASSED, FAILED}

    @Ignore
    public Examinee() {}

    public Examinee(@NonNull String examNumber, String idNumber, String name, String school, int examCount, String examType, int examStatus, int grade, String examinerID) {
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
