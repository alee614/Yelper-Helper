package com.example.yelperhelper;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { FoodListing.class, Review.class}, version = 1, exportSchema = false)
public abstract class ReviewRoomDatabase extends RoomDatabase {
    public abstract ReviewDAO reviewDao();

    private static final String db_name = "tableReview";
    public static ReviewRoomDatabase instance;

    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(4);

    public static synchronized ReviewRoomDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ReviewRoomDatabase.class, db_name).build();
        }
        return instance;
    }

/**
    public ReviewRoomDatabase getDatabase(Context context){
        File dbFile = context.getDatabasePath("YelperHelper-db");
        boolean exists = dbFile.exists();
        // how do i check if it exists or not

        ReviewRoomDatabase db = Room.databaseBuilder(context,
                ReviewRoomDatabase.class, "YelperHelper-db").build();
        return db;
    };
**/


}
