package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.Examinee;

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
    void insertExaminees(Examinee... examinee);

    @Update
    void updateExaminees(Examinee... examinee);

    @Delete
    void deleteExaminees(Examinee... examinee);

    @Query("SELECT * FROM Examinee")
    LiveData<List<Examinee>> getAllExaminees();
}
