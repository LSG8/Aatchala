package com.example.aatchala;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tab1ListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Items> mItems;
    private ItemsViewModel mItemsViewModel;
    private CartHandler mCartItems;

    public Tab1ListAdapter(Context context) {

        LayoutInflater mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_textview_tab1;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        if (!mItems.isEmpty()) {
            Items current = mItems.get(position);
            holder.getView().setText(current.getName() + "\nRs." + current.getPrice() + " per " + current.getUnit());
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = holder.getCheckBox().isChecked();
                    /*Log.d("is checked", String.valueOf(isChecked));
                    Log.d("pos", String.valueOf(position));*/
                    String itemName = mItems.get(position).getName();
                    String itemAmount = holder.getAmount().getText().toString();
                    String itemUnit = mItems.get(position).getUnit();
                    Float unitPrice = mItems.get(position).getPrice();
                    if(itemAmount.matches(""))
                        itemAmount="1";
                    String itemPrice = String.valueOf(Float.valueOf(itemAmount)*unitPrice);
                    String itemDetail = itemName+" : "+itemAmount+" "+itemUnit+", Price: ₹ "+itemPrice;
                    Log.d("selected", itemDetail);

                    if (isChecked)
                        mCartItems.addToCart(itemDetail);
                    else
                        mCartItems.removeFromCart(itemDetail);

                }
            });
        }
        else
            holder.getView().setText("No item");
    }

    public void setItem(List<Items> items) {
        mItems = items;
        //notifyDataSetChanged();
    }

    /*public CartHandler getCartItems() {
        return mCartItems;
    }*/

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }

}
