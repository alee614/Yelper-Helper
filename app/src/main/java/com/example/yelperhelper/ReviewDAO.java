package com.example.yelperhelper;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ReviewDAO {
    // get all the reviews
    @Query("SELECT * FROM tableReview")
    public LiveData<List<Review>> getAllReviews();

    // order review by alphabetical
    @Query("SELECT * FROM tableReview ORDER BY food_id ASC")
    public LiveData<List<Review>> allReviewsInOrder();

    // get list of foodListing
    @Query("SELECT * FROM tableFoodListing")
    public LiveData<List<FoodListing>> getAllFoods();


    @Transaction
    @Query("SELECT * FROM tableFoodListing")
    public LiveData<List<FoodListingWithReviews>> getFoodLisitingWithReviews();

    // get review
    @Query("SELECT * FROM tableReview WHERE review_id = :review_id")
    public LiveData<Review> getSingleReview(int review_id);

    // get foodListing id
    @Query("SELECT food_id FROM tableFoodListing WHERE food_name = :food_name")
    public LiveData<Integer> getFoodListingId(String food_name);

    // insert a new foodlisting
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertFoodListing(FoodListing foodListing);

    // insert a new review, has to have a foodlisting and user
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertReview(Review review);

    // delete a review
    @Query("DELETE FROM tableReview WHERE review_id = :review_id")
    public Completable deleteReview(int review_id);

    // update a review
    @Query("UPDATE tableReview SET food_id = :food_id, review_restaurant = :review_restaurant, review_content = :review_content," +
            " review_rating = :review_rating WHERE review_id = :review_id")
    public Completable updateReview(String food_id, String review_restaurant, String review_content, float review_rating,
                                        int review_id);

}
