package com.example.yelperhelper;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.annotation.Annotation;

@Entity(tableName = "reviewTable")
public class Review {
    @PrimaryKey(autoGenerate = true)
    public int review_id;

    @ColumnInfo(name = "user_id")
    public int user_id;

    @ColumnInfo(name = "food_id")
    public int food_id;

    @ColumnInfo(name = "review_restaurant")
    public String review_restaurant;

    @ColumnInfo(name = "review_content")
    public String review_content;

    @ColumnInfo(name = "review_rating")
    public int review_rating;


}
