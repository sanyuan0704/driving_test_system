package com.whut.driving_test_system.models.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.dao.RuleDao;
import com.whut.driving_test_system.models.eneities.Rule;

import java.util.List;

public class RuleRepository {
    private LiveData<List<Rule>> allLiveRules;
    private Database database;
    private RuleDao ruleDao;

    public LiveData<List<Rule>> getAllLiveRules() {
        return allLiveRules;
    }

    public RuleRepository(Context context) {
        database = Database.getDatabase(context.getApplicationContext());
        ruleDao = database.getRuleDao();
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


    @SuppressLint("StaticFieldLeak")
    public void deleteAllRules() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.getRuleDao().deleteAllRules();
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void updateRules(Rule... rules) {
        new AsyncTask<Rule, Void, Void>() {

            @Override
            protected Void doInBackground(Rule... rules) {
                database.getRuleDao().updateRules(rules);
                return null;
            }
        }.execute(rules);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteRules(Rule... rules) {
        new AsyncTask<Rule,Void,Void>(){

            @Override
            protected Void doInBackground(Rule... rules) {
                database.getRuleDao().deleteRules(rules);
                return null;
            }
        }.execute(rules);
    }
}
