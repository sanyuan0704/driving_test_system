package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.ExamineeRuleRef;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExamineeRuleRefDao {
    @Insert
    void insertExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);

    @Delete
    void deleteExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);

    @Query("DELETE FROM examineeruleref")
    void deleteAllExamineeRuleRef();
}