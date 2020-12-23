package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.ExamineeRuleRef;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ExamineeRuleRefDao {
    @Insert
    void insertExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);

//    @Query("DELETE FROM examineeruleref WHERE examNumber=:examNumber AND ruleId=:ruleId LIMIT 1")
    @Delete
    void deleteExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs);

    @Query("DELETE FROM examineeruleref")
    void deleteAllExamineeRuleRef();

    @Query("SELECT * FROM examineeruleref WHERE examNumber=:examNumber")
    LiveData<List<ExamineeRuleRef>> getExamineeRuleRefByExamnumber(String examNumber);
}