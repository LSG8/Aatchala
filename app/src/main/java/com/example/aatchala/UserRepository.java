package com.example.aatchala;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private List<User> mAllUsers;
    private User userNow;

    public UserRepository(Application application) {
        AatchalaDatabase db = AatchalaDatabase.getDatabase(application);
        //AatchalaDatabase db = AatchalaDatabase.getInstance(application);
        mUserDao = db.userDao();
    }

    public List<User> getAllUsers() {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
            mAllUsers = mUserDao.getAllUsers();
        //});
        return mAllUsers;
    }

    public void insert(User newDBUser) {
        //AatchalaDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.addUser(newDBUser);
        //});
    }

    public void updatePassword(String passNew, String phone) {
        AatchalaDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.updatePassword(passNew, phone);
        });
    }

    public User getUserByPhone(String phone) {
        /*AatchalaDatabase.da
        AatchalaDatabase.databaseWriteExecutor.execute(() -> {*/
            userNow = mUserDao.getUserByPhone(phone);
        //});
        return userNow;
    }

   /* public void insert(User newDBUser){
        mUserDao.addUser(newDBUser);
    }*/

}
