package com.whut.driving_test_system.models.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.whut.driving_test_system.models.eneities.Rule;

import java.util.List;

@Dao
public interface RuleDao {
    @Insert
    void insertRules(Rule... rule);

    @Update
    void updateRules(Rule... rule);

    @Delete
    void deleteRules(Rule... rule);

    @Query("SELECT * FROM Rule ORDER BY ruleId")
    LiveData<List<Rule>> getAllRules();
}
