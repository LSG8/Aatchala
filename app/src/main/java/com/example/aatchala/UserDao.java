package com.example.aatchala;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Query("SELECT * from user_info")
    List<User> getAllUsers();
    //LiveData<List<User>> getAllUsers();

    @Query("SELECT * from user_info where phone= :phoneN")
    public User getUserByPhone(String phoneN);
    //public LiveData<User> getUserByPhone(String phoneN);

    @Query("UPDATE user_info SET pass = :passN where phone= :phoneN")
    public void updatePassword(String passN, String phoneN);

    @Delete
    public void deleteUser(User user);
}
