package com.whut.driving_test_system.models.eneities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String userId;
    public String name;
    public String username;
    public String password;
    public int usertype;


    public static enum UserTypes {Examiner, Admin}
}
