package com.example.aatchala;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ItemsDao {

    @Insert
    public void add(Items item);

    @Query("DELETE FROM items_info")
    public void deleteAll();

    @Query("SELECT * from items_info")
    List<Items> getAllItems();
    //LiveData<List<Items>> getAllItems();

    @Query("SELECT * from items_info where category = :sCategory")
    List<Items> getItemsByCategory(String sCategory);
    //public LiveData<List<Items>> getItemsByCategory(String sCategory);

    @Query("SELECT * from items_info where id = :sId")
    Items getItemById(int sId);
    //public LiveData<Items> getItemById(int sId);

    @Query("UPDATE items_info SET price = :new_price WHERE id = :sId")
    public void updatePrice(int sId, float new_price);

    @Query("UPDATE items_info SET inStock = :new_inStock WHERE id = :sId")
    public void updateInStock(int sId, Boolean new_inStock);

}
