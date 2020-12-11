package com.whut.driving_test_system.models;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.whut.driving_test_system.models.dao.RuleDao;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.Rule;

@Database(entities = {Rule.class, Examinee.class}, version = 1, exportSchema = false)
public abstract class TestDatabase extends RoomDatabase {
    private static TestDatabase INSTANCE;
    public static synchronized TestDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TestDatabase.class,
                    "test_database"
            ).build();
        }
        return INSTANCE;
    }
    public abstract RuleDao getRuleDao();
}
