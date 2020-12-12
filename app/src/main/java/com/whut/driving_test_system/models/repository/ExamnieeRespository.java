package com.whut.driving_test_system.models.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.eneities.Examinee;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ExamnieeRespository {
    private Database database;

    public ExamnieeRespository(Context context) {
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
    public void deleteAllExamniee(){
        new AsyncTask<Void,Void,Void>(){

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
}
