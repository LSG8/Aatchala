package com.example.aatchala;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class Tab1Fragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ItemsViewModel mItemsViewModel;
    private Tab1ListAdapter adapter;
    private List<Items> items =new ArrayList<>();
    private Button addCartButton;
    private Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        addCartButton = (Button) getActivity().findViewById(R.id.addToCart);
        recyclerView = view.findViewById(R.id.recyclerview1);
        adapter = new Tab1ListAdapter(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mItemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);
        //items = mItemsViewModel.getItemsByCategory(getResources().getString(R.string.tab1));
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = database.child("items/Vegetable");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                items.clear();
                adapter.setItem(items);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Items item1 = snapshot.getValue(Items.class);
                    items.add(i,item1);
                    i++;
                    mItemsViewModel.updatePrice(item1.getId(),item1.getPrice());
                }
                recyclerView.setAdapter(adapter);
                adapter.setItem(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        addCartButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(session.checkLogin())
            startActivity(new Intent(getActivity(), ViewCartActivity.class));
        else
            Toast.makeText(getActivity().getApplicationContext(), "Please log in first", Toast.LENGTH_LONG).show();

    }
}

