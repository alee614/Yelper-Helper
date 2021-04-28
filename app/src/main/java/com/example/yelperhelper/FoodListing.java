package com.example.yelperhelper;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableFoodListing")
public class FoodListing {
    @PrimaryKey(autoGenerate = true)
    public int food_id;
    @ColumnInfo(name = "food_name")
    public String food_name;

}
