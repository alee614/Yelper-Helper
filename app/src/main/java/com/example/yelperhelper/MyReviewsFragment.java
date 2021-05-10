package com.example.yelperhelper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyReviewsFragment extends Fragment {
    // this fragment loads a list of the foods that the user has listed
    // or you can add food item/list

    private View view;
    private Button button;
    private RecyclerView recyclerView;
    private List<Review> reviews;
    private ReviewViewModel reviewViewModel;
    private ReviewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myreviews, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        button = view.findViewById(R.id.buttonadd);
        recyclerView = view.findViewById(R.id.recyclerView_reviews);

        reviewViewModel.getAllReviews().observe(getViewLifecycleOwner(), reviewObserver);

        button.setOnClickListener(v -> {
            reviewViewModel.allReviewsInOrder().observe(getViewLifecycleOwner(), reviewObserver);
        });


        // based on who the user is
        // load their list of different foods

        return view;
    }


    final Observer<List<Review>> reviewObserver = new Observer<List<Review>>() {

        @Override
        public void onChanged(List<Review> reviews) {
            if (reviews.size() == 0){
                Log.e("this", "is empty");
            }
            adapter = new ReviewAdapter(reviews);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
    };
}
