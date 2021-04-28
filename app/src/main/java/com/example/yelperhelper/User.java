package com.example.yelperhelper;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableUser")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int user_id;

    @ColumnInfo(name = "user_uid")
    public String user_uid;

}
