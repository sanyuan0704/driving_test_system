package com.whut.driving_test_system.models.eneities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Examinee {

    @PrimaryKey
    private String examNumber;
    private String idNumber;
    private String name;
    private String school;
    private int examCount;
    private String examType;
    private String examStatus;
    private int grade;

    public Examinee(String examNumber, String idNumber, String name, String school, int examCount, String examType, String examStatus, int grade) {
        this.examNumber = examNumber;
        this.idNumber = idNumber;
        this.name = name;
        this.school = school;
        this.examCount = examCount;
        this.examType = examType;
        this.examStatus = examStatus;
        this.grade = grade;
    }

    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getExamCount() {
        return examCount;
    }

    public void setExamCount(int examCount) {
        this.examCount = examCount;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
