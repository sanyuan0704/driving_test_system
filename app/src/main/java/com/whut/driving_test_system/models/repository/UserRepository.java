package com.whut.driving_test_system.models.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.whut.driving_test_system.models.Database;
import com.whut.driving_test_system.models.eneities.User;
import com.whut.driving_test_system.models.eneities.UserWithExaminees;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private Database database;

    public UserRepository(Context context) {
        this.database = Database.getDatabase(context);
    }

    public UserRepository() {

    }

    @SuppressLint("StaticFieldLeak")
    public void insertUsers(User... users) {
        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {
                database.getUserDao().insertUsers(users);
                return null;
            }
        }.execute(users);
    }

    @SuppressLint("StaticFieldLeak")
    public void updateUsers(User... users) {
        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {
                database.getUserDao().updateUsers(users);
                return null;
            }
        }.execute(users);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteUsers(User... users) {
        new AsyncTask<User, Void, Void>() {

            @Override
            protected Void doInBackground(User... users) {
                database.getUserDao().deleteUsers(users);
                return null;
            }
        }.execute(users);
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteAllUsers() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                database.getUserDao().deleteAllUsers();
                return null;
            }
        }.execute();
    }

    public LiveData<User> getUserById(String userId) {
        return database.getUserDao().getUserById(userId);
    }

    public LiveData<List<User>> getAllUsers() {
        return database.getUserDao().getAllUsers();
    }

    public LiveData<UserWithExaminees> getUserWithExamineesById(String userId) {
        return database.getUserDao().getUserWithExamineesById(userId);
    }

    public LiveData<User> getUserByUsernameAndPassword(String username, String password) {
        return database.getUserDao().getUserByUsernameAndPassword(username, password);
    }
}
