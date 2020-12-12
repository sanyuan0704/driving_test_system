package com.whut.driving_test_system.models.dao;

import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.eneities.UserWithExaminees;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void insertUsers(User... user);

    @Update
    void updateUsers(User... user);

    @Delete
    void deleteUsers(User... user);

    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Transaction
    @Query("SELECT * FROM user WHERE userId = :userId")
    LiveData<UserWithExaminees> getUserWithExamineesById(String userId);
}
