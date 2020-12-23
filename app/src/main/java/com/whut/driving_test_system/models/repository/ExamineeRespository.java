package com.whut.driving_test_system.models.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.eneities.Examinee;
import com.whut.driving_test_system.models.eneities.ExamineeRuleRef;
import com.whut.driving_test_system.models.eneities.ExamineeWithRules;
import com.whut.driving_test_system.models.eneities.Rule;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ExamineeRespository {
    private Database database;

    public ExamineeRespository(Context context) {
        this.database = Database.getDatabase(context);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertExaminees(Examinee... examinees) {
        new AsyncTask<Examinee, Void, Void>() {

            @Override
            protected Void doInBackground(Examinee... examinees) {
                database.getExamnieeDao().insertExaminees(examinees);
                return null;
            }
        }.execute(examinees);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateExaminees(Examinee... examinees) {
        new AsyncTask<Examinee, Void, Void>() {

            @Override
            protected Void doInBackground(Examinee... examinees) {
                database.getExamnieeDao().updateExaminees(examinees);
                return null;
            }
        }.execute(examinees);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteExaminees(Examinee... examinees) {
        new AsyncTask<Examinee, Void, Void>() {

            @Override
            protected Void doInBackground(Examinee... examinees) {
                database.getExamnieeDao().deleteExaminees(examinees);
                return null;
            }
        }.execute(examinees);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteAllExamniee() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.getExamnieeDao().deleteAllExamniee();
                return null;
            }
        }.execute();
    }

    public LiveData<List<Examinee>> getAllExaminees() {
        return database.getExamnieeDao().getAllExaminees();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertExamnieeRuleRef(final Examinee examinee, final Rule rule) {
        new AsyncTask<ExamineeRuleRef, Void, Void>() {

            @Override
            protected Void doInBackground(ExamineeRuleRef... examineeRuleRefs) {
                ExamineeRuleRef examineeRuleRef = new ExamineeRuleRef();
                examineeRuleRef.examNumber = examinee.examNumber;
                examineeRuleRef.ruleId = rule.ruleId;
                database.getExamnieeRuleRefDao().insertExamnieeRuleRef(examineeRuleRef);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteExamnieeRuleRef(ExamineeRuleRef... examineeRuleRefs) {
        new AsyncTask<ExamineeRuleRef,Void,Void>(){

            @Override
            protected Void doInBackground(ExamineeRuleRef... examineeRuleRefs) {
                database.getExamnieeRuleRefDao().deleteExamnieeRuleRef(examineeRuleRefs);
                return null;
            }
        }.execute(examineeRuleRefs);
    }


    @SuppressLint("StaticFieldLeak")
    public void deleteAllExamineeRuleRef() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.getExamnieeRuleRefDao().deleteAllExamineeRuleRef();
                return null;
            }
        }.execute();
    }

    public LiveData<List<ExamineeRuleRef>> getExamineeRuleRefByExamnumber(String examNumber) {
        return database.getExamnieeRuleRefDao().getExamineeRuleRefByExamnumber(examNumber);
    }
}
