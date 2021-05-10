package com.example.yelperhelper;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FindFragment extends Fragment {
    private View view;
    private DatabaseReference mDatabase;


    private RecyclerView recyclverView_business;
    private List<Business> businesses;
    private FindAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        recyclverView_business = view.findViewById(R.id.recyclerView_business);
        //adapter = new FindAdapter(businesses);
        //recyclverView_business.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclverView_business.setAdapter(adapter);



        mDatabase = FirebaseDatabase.getInstance().getReference().child("yelper-helper-default=rtdb");
        mDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.d("firebase", String.valueOf(task.getResult().getValue()));

            }
        });



        return view;
    }

}