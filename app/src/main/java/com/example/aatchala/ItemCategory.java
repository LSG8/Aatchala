package com.example.aatchala;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_category")
public class ItemCategory {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "categoryName")
    private String categoryName;

    @Ignore
    public ItemCategory() {
    }

    public ItemCategory(@NonNull int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}



