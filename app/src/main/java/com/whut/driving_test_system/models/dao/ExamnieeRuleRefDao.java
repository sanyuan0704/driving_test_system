package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.ExamineeRuleRef;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface ExamnieeRuleRefDao {
    @Insert
    void insertExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);

    @Delete
    void deleteExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);
}