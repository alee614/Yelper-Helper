package com.example.yelperhelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class UserFragment extends Fragment {
    // this fragment loads a list of the foods that the user has listed
    // or you can add food item/list

    private View view;
    private Button button;
    private RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        button = view.findViewById(R.id.buttonadd);
        recyclerView = view.findViewWithTag(R.id.recyclerView);

        // based on who the user is
        // load their list of different foods



        return view;
    }
}
