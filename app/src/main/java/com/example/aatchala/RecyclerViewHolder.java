package com.example.aatchala;


import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView viewItem;
    private CheckBox tabCheck;
    private EditText amountEdit;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        viewItem = itemView.findViewById(R.id.tabText);
        tabCheck = (CheckBox) itemView.findViewById(R.id.checkBoxTab);
        amountEdit = (EditText) itemView.findViewById(R.id.amountText);
    }

    public TextView getView() {
        return viewItem;
    }

    public CheckBox getCheckBox() {
        return tabCheck;
    }

    public EditText getAmount() {
        return amountEdit;
    }
}

