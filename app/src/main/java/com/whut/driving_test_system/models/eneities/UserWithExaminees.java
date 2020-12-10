package com.whut.driving_test_system.models.eneities;


import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserWithExaminees {
    @Embedded public User user;
    @Relation(
            parentColumn = "ID",
            entityColumn = "examinerID"
    )
    public List<Examinee> examinees;
}
