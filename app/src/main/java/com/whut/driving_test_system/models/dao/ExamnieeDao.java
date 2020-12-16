package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.ExamnieeWithRules;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ExamnieeDao {
    @Insert
    void insertExaminees(Examinee... examinees);

    @Update
    void updateExaminees(Examinee... examinees);

    @Delete
    void deleteExaminees(Examinee... examinees);

    @Query("DELETE FROM examinee")
    void deleteAllExamniee();

    @Query("SELECT * FROM Examinee")
    LiveData<List<Examinee>> getAllExaminees();

    @Query("SELECT * FROM Examinee WHERE examNumber=:examNumber")
    LiveData<ExamnieeWithRules> getExamnieeWithRulesByExamnumber(String examNumber);
}
