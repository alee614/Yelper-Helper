package com.example.yelperhelper;

import android.app.Application;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Flowable;

class ReviewRepository {
    private ReviewDAO reviewDAO;

    ReviewRepository(Application application){
        ReviewRoomDatabase reviewRoomDatabase = ReviewRoomDatabase.getInstance(application);
        reviewDAO = reviewRoomDatabase.reviewDao();
    }



    LiveData<List<Review>> allReviewsInOrder() {return reviewDAO.allReviewsInOrder();}

    LiveData<List<Review>> getAllReviews(){
        return reviewDAO.getAllReviews();
    }

    LiveData<List<FoodListing>> getAllFoods(){ return reviewDAO.getAllFoods(); }


    LiveData<Review> getSingleReview(int review_id){
        return reviewDAO.getSingleReview(review_id);
    }

    LiveData<Integer> getFoodListingId(String food_name){
        return reviewDAO.getFoodListingId(food_name);
    }

    void insertFoodListing(FoodListing foodlisting){
        ReviewRoomDatabase.databaseWriterExecutor.execute(() -> {
            reviewDAO.insertFoodListing(foodlisting);
        });
    }

    void insertReview(Review review){
        ReviewRoomDatabase.databaseWriterExecutor.execute(() -> {
            reviewDAO.insertReview(review);
        });
    }

    void deleteReview(int review_id){
        ReviewRoomDatabase.databaseWriterExecutor.execute(() -> {
            reviewDAO.deleteReview(review_id);
        });
    }

    void updateReview(String food_id, String review_restaurant, String review_content, float review_rating, int review_id){
        ReviewRoomDatabase.databaseWriterExecutor.execute(() -> {
            reviewDAO.updateReview(food_id, review_restaurant, review_content, review_rating, review_id);
        });
    }







}
