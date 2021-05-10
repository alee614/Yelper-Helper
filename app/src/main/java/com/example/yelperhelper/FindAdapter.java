package com.example.yelperhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private List<Business> businesses;

    public FindAdapter(List<Business> businesses) {this.businesses = businesses;}

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_businessName;
        TextView textView_businessAddress;
        Button button_call;
        RatingBar ratingBar_business;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textView_businessName  = itemView.findViewById(R.id.textView_businessName);
            textView_businessAddress = itemView.findViewById(R.id.textView_businessAddress);
            button_call = itemView.findViewById(R.id.button_call);
            ratingBar_business = itemView.findViewById(R.id.ratingBar_business);
        }
    }

    @NonNull
    @Override
    public FindAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View findView = inflater.inflate(R.layout.item_find, parent, false);
        ViewHolder viewHolder = new ViewHolder(findView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ViewHolder holder, int position) {
        Business business = businesses.get(position);
        holder.textView_businessName.setText(business.getName());
        holder.textView_businessAddress.setText(business.getAddress());
        holder.ratingBar_business.setRating(business.getRating());


    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }
}
