package com.example.yelperhelper;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.annotation.Annotation;

@Entity(tableName = "tableReview")
public class Review {
    @PrimaryKey(autoGenerate = true)
    public int review_id;

    @ColumnInfo(name = "food_id")
    public String food_id;

    @ColumnInfo(name = "review_restaurant")
    public String review_restaurant;

    @ColumnInfo(name = "review_content")
    public String review_content;

    @ColumnInfo(name = "review_rating")
    public float review_rating;

    @ColumnInfo( name = "image_path")
    public String image_path;

    public Review(String food_id, String review_restaurant, String review_content, float review_rating, String image_path){
        this.food_id = food_id;
        this.review_restaurant = review_restaurant;
        this.review_content = review_content;
        this.review_rating = review_rating;
        this.image_path = image_path;
    }


}
