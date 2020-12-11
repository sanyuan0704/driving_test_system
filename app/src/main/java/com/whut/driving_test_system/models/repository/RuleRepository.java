package com.whut.driving_test_system.models.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.whut.driving_test_system.models.TestDatabase;
import com.whut.driving_test_system.models.dao.RuleDao;
import com.whut.driving_test_system.models.eneities.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleRepository {
    private LiveData<List<Rule>> allLiveRules;
    private TestDatabase testDatabase;
    private RuleDao ruleDao;
    public LiveData<List<Rule>> getAllLiveRules() {
        return allLiveRules;
    }

    public RuleRepository(Context context) {
        testDatabase = TestDatabase.getDatabase(context.getApplicationContext());
        ruleDao = testDatabase.getRuleDao();
        allLiveRules = ruleDao.getAllRules();
    }

    public void insertRule(Rule... rules) {
        new InsertAsyncTask(ruleDao).execute(rules);
    }

    static class InsertAsyncTask extends AsyncTask<Rule, Void, Void> {
        private RuleDao ruleDao;

        public InsertAsyncTask(RuleDao ruleDao) {
            this.ruleDao = ruleDao;
        }

        @Override
        protected Void doInBackground(Rule... rules) {
            ruleDao.insertRules(rules);
            return null;
        }
    }
}
