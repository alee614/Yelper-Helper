package com.example.yelperhelper;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews){
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View reviewView = inflater.inflate(R.layout.item_myreviews, parent, false);
        ViewHolder viewHolder = new ViewHolder(reviewView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textView_food.setText(review.food_id);
        holder.textView_location.setText(review.review_restaurant);
        holder.ratingBar_myreviews.setRating(review.review_rating);

        holder.constraintLayout_myreviews.setOnClickListener(v -> {
            Context context = v.getContext();
            //Intent intent = new Intent(context, ReviewInfoActivity.class);
            //intent.putExtra("reviewItem", review);
            //context.startActivity(intent);
        });



    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView_food;
        TextView textView_location;
        RatingBar ratingBar_myreviews;
        ConstraintLayout constraintLayout_myreviews;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textView_food = itemView.findViewById(R.id.textView_food);
            textView_location = itemView.findViewById(R.id.textView_location);
            ratingBar_myreviews = itemView.findViewById(R.id.ratingBar_myreviews);
            constraintLayout_myreviews = itemView.findViewById(R.id.constraintLayout_myreviews);

        }

        @Override
        public void onClick(View v) {
            int selected = getAdapterPosition();
            Review selectedReview = reviews.get(selected);
            // send as an intent and open More info window
            Log.e("you have selected", String.valueOf(selected));


        }
    }


}
