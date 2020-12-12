package com.whut.driving_test_system.models;

import android.content.Context;

import com.whut.driving_test_system.models.dao.ExamnieeDao;
import com.whut.driving_test_system.models.dao.RuleDao;
import com.whut.driving_test_system.models.dao.UserDao;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.ExamineeRuleRef;
import com.whut.driving_test_system.models.eneities.Rule;
import com.whut.driving_test_system.models.eneities.User;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class, Examinee.class, Rule.class, ExamineeRuleRef.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database INSTANCE;

    public synchronized static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    Database.class,
                    "database"
            ).build();
        }
        return INSTANCE;
    }

    public abstract UserDao getUserDao();

    public abstract ExamnieeDao getExamnieeDao();

    public abstract RuleDao getRuleDao();
}
