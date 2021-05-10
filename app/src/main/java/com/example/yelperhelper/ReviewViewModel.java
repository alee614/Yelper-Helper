package com.example.yelperhelper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.Flowable;

public class ReviewViewModel extends AndroidViewModel {

    private ReviewRepository reviewRepository;

    public ReviewViewModel(@NonNull Application application) {
        super(application);
        reviewRepository = new ReviewRepository(application);
    }

    public MutableLiveData<String> userToken = new MutableLiveData<>();

    LiveData<List<Review>> allReviewsInOrder() {return reviewRepository.allReviewsInOrder();}

    LiveData<List<Review>> getAllReviews(){
        return reviewRepository.getAllReviews();
    }

    LiveData<List<FoodListing>> getAllFoods(){
        return reviewRepository.getAllFoods();
    }


    LiveData<Review> getSingleReview(int review_id){
        return reviewRepository.getSingleReview(review_id);
    }

    LiveData<Integer> getFoodListingId(String food_name){
        return reviewRepository.getFoodListingId(food_name);
    }

    public void insertFoodListing(FoodListing foodListing){
        reviewRepository.insertFoodListing(foodListing);
    }

    public void insertReview(Review review){
        reviewRepository.insertReview(review);
    }

    public void deleteReview(int review_id){
        reviewRepository.deleteReview(review_id);
    }

    public void updateReview(String food_id, String review_restaurant, String review_content, float review_rating, int review_id){
        reviewRepository.updateReview(food_id, review_restaurant, review_content, review_rating, review_id);
    }



}
