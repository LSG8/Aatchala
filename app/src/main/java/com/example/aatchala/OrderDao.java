package com.example.aatchala;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * from order_info")
    LiveData<List<Order>> getOrders();
}
