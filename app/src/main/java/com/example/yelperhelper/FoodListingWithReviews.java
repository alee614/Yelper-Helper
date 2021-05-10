package com.example.yelperhelper;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class FoodListingWithReviews {

    // one food listing for many reviews (one-to-many relationship)
    @Embedded public FoodListing foodListing;
    @Relation(
            parentColumn = "food_id",
            entityColumn = "review_id"
    )
    public List<Review> reviews;

}
