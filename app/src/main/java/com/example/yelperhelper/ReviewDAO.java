package com.example.yelperhelper;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.concurrent.Flow;

@Dao
public interface ReviewDAO {
    // get all the reviews
    //@Query("SELECT * FROM reviewTable")


    // get all the reviews given a user
    @Query("SELECT * FROM reviewTable WHERE user_id = :user_id")
    public List<Review> findReviewOfUser(int user_id);

    // get all the food listing given a user

    // insert a new foodlisting

    // insert a new review, has to have a foodlisting and user
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertNewReview(Review review);

}
