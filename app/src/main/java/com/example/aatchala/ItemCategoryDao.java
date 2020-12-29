package com.example.aatchala;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemCategoryDao {

    @Insert
    public void addCategory(ItemCategory type);

    @Query("SELECT * from item_category")
    public LiveData<List<ItemCategory>> getCategoryNames();

}
